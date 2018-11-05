package com.abwebmobile.karl.zslombard.ChatBotClasses;


import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.ArrayList;
import java.util.List;

import static com.google.maps.android.SphericalUtil.computeDistanceBetween;

/**
 * Created by Karl on 02.03.2018.
 * dialog, which provides googleMap for user and show the shortes way by his location
 */

public class ChatBotDialogTrack extends DialogFragment implements TaskRequestCallback {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    GoogleMap mGoogleMap;
    MapView mMapView;
    List<LatLng> mAllPoints;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setRetainInstance(true);
        getDialog().setTitle("КАРТА");
        View v = inflater.inflate(R.layout.dialog_chatbot_track, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
//логика отображения гугл карты, нанесение точек
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;
                //поворот и перемещение камеры карты
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(50.45, 30.45))
                        .zoom(12)
                        .build();
                CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                mGoogleMap.moveCamera(camUpdate);
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(50.451271, 30.458920))
                        .title("тут"));
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(50.471271, 30.478920))
                        .title("тут"));
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(50.491271, 30.498920))
                        .title("тут"));

                mAllPoints = new ArrayList<>();
                mAllPoints.add(new LatLng(50.451271, 30.458920));
                mAllPoints.add(new LatLng(50.471271, 30.478920));
                mAllPoints.add(new LatLng(50.491271, 30.498920));

                if (checkPermission()) {
                    mGoogleMap.setMyLocationEnabled(true);
                    Log.d("gooogle", "ok called");
                    mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                        @Override
                        public boolean onMyLocationButtonClick() {
                            Location location = getLocation();
                            if (location!=null) {
                                Log.d("gooogle", "ok clicked");
                                LatLng nearestPoint = getNearestPoint(new LatLng(location.getLatitude(), location.getLongitude()), mAllPoints);
                                String requestUrl = getRequestUrl(new LatLng(location.getLatitude(), location.getLongitude()), nearestPoint);
                                requestPolyline(requestUrl);
                                if (mAllPoints.size() > 1) {
                                    mAllPoints.remove(nearestPoint);
                                    LatLng nextNearestPoint = getNearestPoint(new LatLng(location.getLatitude(), location.getLongitude()), mAllPoints);
                                    String requestNextUrl = getRequestUrl(new LatLng(location.getLatitude(), location.getLongitude()), nextNearestPoint);
                                    requestPolyline(requestNextUrl);
                                }
                            }else{
                                Toast.makeText(getActivity().getApplicationContext(), "Переконайтесь, що геодані увімкнені", Toast.LENGTH_SHORT).show();
                            }

                            return false;
                        }
                    });
                }

            }
        });


        return v;
    }

    private LatLng getNearestPoint(LatLng startPoint, List<LatLng> allPoints) {
        Double shortestDistance = 99999999D;
        int nearestPointPlace = 0;
        for (int i=0;i<allPoints.size();i++){
            double distance = computeDistanceBetween(startPoint, allPoints.get(i));
            if (distance<shortestDistance){shortestDistance = distance;
                nearestPointPlace = i;
            }
        }

        return allPoints.get(nearestPointPlace);
    }

    private void requestPolyline(String requestUrl) {
        TaskRequestDirections taskRequestDirections = new TaskRequestDirections(this);
        taskRequestDirections.execute(requestUrl);
    }


    private String getRequestUrl(LatLng origin, LatLng dest) {
        //Value of origin
        String str_org = "origin=" + origin.latitude +","+origin.longitude;
        //Value of destination
        String str_dest = "destination=" + dest.latitude+","+dest.longitude;
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org +"&" + str_dest + "&" +sensor+"&" +mode;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }



    private Location getLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));

    }


    boolean checkPermission(){
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
            return true;
        } else {
            // Show rationale and request permission.
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
           return false;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }



    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }
    int colorLine = Color.BLUE;
    @Override
    public void onCallback(PolylineOptions polylineOptions) {
        polylineOptions.width(15);
        polylineOptions.color(colorLine);
        if(colorLine==Color.BLUE){
            colorLine = getResources().getColor(R.color.transparentBlue);
        }else{
            colorLine = Color.BLUE;
        }
        polylineOptions.geodesic(true);
        mGoogleMap.addPolyline(polylineOptions);

    }
}
