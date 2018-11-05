package com.abwebmobile.karl.zslombard.notification;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.abwebmobile.karl.zslombard.HostView;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.SettingsState;
import com.abwebmobile.karl.zslombard.allFragments.ApplianceRequestDialog;
import com.abwebmobile.karl.zslombard.allFragments.ConsultationRequestDialog;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.Callbacker;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;

import static com.abwebmobile.karl.zslombard.Constants.NOTIFICATION_FRAGMENT;

/**
 * Created by Karl on 09.03.2018.
 */

public class NotificationFragment extends android.support.v4.app.Fragment implements Callbacker {
    InfoViewModel mViewModel;
    HostView hostView;
    SettingsState mSettingState;
    CheckBox checkReminder, checkSales, checkOther;

    public void setHostView(HostView hostView) {
        this.hostView = hostView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        setRetainInstance(true);
    }
    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification_settings, container, false);
       /* v.findViewById(R.id.buttonSetAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        v.findViewById(R.id.buttonSetNothing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCustomSettingState();
            }
        });*/

        checkOther = (CheckBox)v.findViewById(R.id.checkBoxOthers);
        checkSales = (CheckBox)v.findViewById(R.id.checkBoxNews);
        checkReminder = (CheckBox)v.findViewById(R.id.checkBoxReminder);
        return v;
    }

    private void saveCustomSettingState() {
        SettingsState ss = new SettingsState();
        ss.checkBoxOtherState = checkOther.isChecked();
        ss.checkBoxSalesState = checkSales.isChecked();
        ss.checkBoxReminderState = checkReminder.isChecked();
        MutableLiveData<SettingsState> sd = new MutableLiveData<>();
        sd.setValue(ss);
        mViewModel.saveSettingsState(sd, this);
    }

    @Override
    public void onPause() {
        Log.d("SHIT", "onPause");
        saveCustomSettingState();

        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("SHIT", "onResume");
        MutableLiveData<SettingsState> sst = mViewModel.getCurrentSettingsState();
        sst.observe(this, new Observer<SettingsState>() {
            @Override
            public void onChanged(@Nullable SettingsState settingsState) {
                if(settingsState==null){
                    setDefaultSettingState();
                }
                Log.d("SHIT", "in observer onChanged");
                if (settingsState!=null) {
                    Log.d("SHIT", "with params = " + settingsState.checkBoxSalesState);
                    checkSales.setChecked(settingsState.checkBoxSalesState);
                    checkOther.setChecked(settingsState.checkBoxOtherState);
                    checkReminder.setChecked(settingsState.checkBoxReminderState);
                }else{
                    Log.d("SHIT", "with params = null");
                }
            }
        });
        super.onResume();
    }

    private void setDefaultSettingState() {
        mViewModel.addDeafultSettingsState(this);
    }

    @Override
    public void onCallback(String command) {
Log.d("SHIT", "onCallback in fragment called");
    }
}