package com.abwebmobile.karl.zslombard.allFragments;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.qrStaff.QRDataListener;
import com.abwebmobile.karl.zslombard.qrStaff.QREader;
import com.abwebmobile.karl.zslombard.qrStaff.RPResultListener;
import com.abwebmobile.karl.zslombard.qrStaff.RuntimePermissionUtil;

import static com.abwebmobile.karl.zslombard.Constants.LOGIN_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.QREADER_FRAGMENT;

/**
 * Created by Karl on 07.02.2018.
 */

public class QReaderFragment extends Fragment implements CameraChanger {

    private static final String cameraPerm = Manifest.permission.CAMERA;
    HostView hostView;
    String scannedQR;



    int cameraChosed = QREader.BACK_CAM;

    public void setCameraChosed(int cameraChosed) {
        this.cameraChosed = cameraChosed;
    }

    public void setHostView(HostView hostView) {
        this.hostView = hostView;
    }

    // UI
    private TextView text;

    // QREader
    private SurfaceView mySurfaceView;
    private QREader qrEader;
    LinearLayout surfaceCorners;
    boolean hasCameraPermission = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_qr, container, false);

        hasCameraPermission = RuntimePermissionUtil.checkPermissonGranted(getActivity(), cameraPerm);
        text = v.findViewById(R.id.code_info);
        Log.d("qrDetails", "onCreateView called");

        surfaceCorners = v.findViewById(R.id.surfaceCorners);
        final Button stateBtn =v.findViewById(R.id.btn_start_stop);
        // change of reader state in dynamic
        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (qrEader.isCameraRunning()) {
                    qrEader.stop();
                }
                Log.d("qrlog", "now scanned qr is "+scannedQR);
                if (scannedQR==null)scannedQR="";
                hostView.requestFragment(LOGIN_FRAGMENT, scannedQR);
            }
        });

        stateBtn.setVisibility(View.VISIBLE);

        Button restartbtn = v.findViewById(R.id.btn_restart_activity);
        restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartFragment(false);
            }
        });
        ImageButton switchCamera = v.findViewById(R.id.switchCameraButton);
        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartFragment(true);
            }
        });


        // Setup SurfaceView
        // -----------------
        mySurfaceView = v.findViewById(R.id.camera_view);

        if (hasCameraPermission) {
            // Setup QREader
            setupQREader();
        } else {
            Log.d("qrDetails", "try to request permission");
            RuntimePermissionUtil.requestPermission(getActivity(), cameraPerm, 100);
        }


        return v;
    }
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("qrDetails", "onCreate");



    }

    void restartFragment(boolean needSwitch) {
        Log.d("qrDetails", "called restart fragment");
        int toChoose;
        if (needSwitch) {
           toChoose = (cameraChosed == QREader.BACK_CAM) ? (QREader.FRONT_CAM) : (QREader.BACK_CAM);
        }else{
            toChoose = cameraChosed;
        }
        hostView.requestFragment(QREADER_FRAGMENT, String.valueOf(toChoose));

    }

    void setupQREader() {
        // Init QREader
        // ------------
        Log.d("qrDetails", "call SetupQreader");
        qrEader = new QREader.Builder( this, getActivity(), mySurfaceView, new QRDataListener() {

            @Override
            public void onDetected(final String data) {
                Log.d("qrDetails", "all builded and in onProcess");
                Log.d("QREader", "Value : " + data);
                scannedQR = data;
                text.post(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(data);
                        surfaceCorners.setBackgroundResource(R.drawable.shadow_green);

                    }
                });
            }
        }).facing(cameraChosed)
                .enableAutofocus(true)
                .height(mySurfaceView.getHeight())
                .width(mySurfaceView.getWidth())
                .build();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("qrDetails", "called onPause");
        if (hasCameraPermission) {

            // Cleanup in onPause()
            // --------------------

            qrEader.releaseAndCleanup();
            Log.d("qrDetails", "release and cleanUp in onPause");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("qrDetails", "called onResume");
        hasCameraPermission = RuntimePermissionUtil.checkPermissonGranted(getActivity(), cameraPerm);
        Log.d("qrDetails", "permission is "+ hasCameraPermission);
        if (hasCameraPermission) {

            // Init and Start with SurfaceView
            // -------------------------------
            if(qrEader==null){
            setupQREader();
            restartFragment(false);
            }

            qrEader.initAndStart(mySurfaceView);
            qrEader.start();

            Log.d("qrDetails", "init and start camera in OnResume");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, final String[] permissions,
                                            final int[] grantResults) {
        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionGranted() {
                    if ( RuntimePermissionUtil.checkPermissonGranted(getActivity(), cameraPerm)) {
                        Log.d("qrDetails", "in onRequestPermissionResult called setupQreader");
                        setupQREader();
                        qrEader.initAndStart(mySurfaceView);
                    }
                }

                @Override
                public void onPermissionDenied() {
                    // do nothing
                    Log.d("qrDetails", "in onRequestPermissionResult called denied permission");
                }
            });
        }
    }

    @Override
    public void changeCamera(int frontCam) {
        Log.d("qrDetails", "called change camera");
    }
}
