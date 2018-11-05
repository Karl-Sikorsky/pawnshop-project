package com.abwebmobile.karl.zslombard.allFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.R;

/**
 * Created by Karl on 04.02.2018.
 * Фрагмент, отображающий условия кредитирования бытовой техники
 */


public class ConditionsAppliancesFragment extends android.support.v4.app.Fragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.fragment_conditions_appliances, container, false);
            v.findViewById(R.id.consultationFromAppliance).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConsultationRequestDialog consultationRequestDialog = new ConsultationRequestDialog();
                    consultationRequestDialog.show(getChildFragmentManager(),"ЗАМОВИТИ КОНСУЛЬТАЦІЮ");
                }
            });
            v.findViewById(R.id.idSendApplianceRequest).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApplianceRequestDialog applianceRequestDialog = new ApplianceRequestDialog();
                    applianceRequestDialog.show(getChildFragmentManager(), "ВІДПРАВИТИ ЗАЯВКУ");
                }
            });
            return v;
        }

}
