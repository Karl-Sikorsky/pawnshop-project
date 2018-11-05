package com.abwebmobile.karl.zslombard.allFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;

import static com.abwebmobile.karl.zslombard.Constants.FRAGMENT_CALCULATOR;

/**
 * Created by Karl on 04.02.2018.
 * Фрагмент, отвечающий за условия кредитирования ювелирных изделий
 */


public class ConditionsJewerlyFragment extends android.support.v4.app.Fragment {
    HostView mHostView;

    public void setHostView(HostView hostView) {
        this.mHostView = hostView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_conditions_jewerly, container, false);
        v.findViewById(R.id.consultationFromJewerly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultationRequestDialog consultationRequestDialog = new ConsultationRequestDialog();
                consultationRequestDialog.show(getChildFragmentManager(),"ЗАМОВИТИ КОНСУЛЬТАЦІЮ");
            }
        });
        v.findViewById(R.id.calculatorButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHostView.requestFragment(FRAGMENT_CALCULATOR, "");
            }
        });
        return v;
    }
}
