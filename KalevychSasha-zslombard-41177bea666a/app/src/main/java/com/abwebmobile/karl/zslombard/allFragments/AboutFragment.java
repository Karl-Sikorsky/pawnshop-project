package com.abwebmobile.karl.zslombard.allFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abwebmobile.karl.zslombard.R;
import com.firebase.ui.database.FirebaseIndexArray;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Karl on 04.02.2018.
 */

public class AboutFragment extends Fragment  {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
//тут фактично лише викликається діалогове вікно консультації
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);
        v.findViewById(R.id.buttonRequestFromAbout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultationRequestDialog consultationRequestDialog = new ConsultationRequestDialog();
                consultationRequestDialog.show(getChildFragmentManager(),"ЗАМОВИТИ КОНСУЛЬТАЦІЮ");
                //ok TODO: send input and set remote for input
                consultationRequestDialog.onClick(new Button(getActivity()));
                Log.d("set attribute", "null catcher");
                

            }
        });
        return v;
    }
}
