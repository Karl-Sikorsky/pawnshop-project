package com.abwebmobile.karl.zslombard.allFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abwebmobile.karl.zslombard.R;

/**
 * Created by Karl on 04.02.2018.
 * Диалог, отвечающий за заполнение формы отправки бытовой техники на оценку
 */

public class ApplianceRequestDialog extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        getDialog().setTitle("ЗАЛИШИТИ ЗАЯВКУ");
        View v = inflater.inflate(R.layout.appliance_request, null);
        v.findViewById(R.id.buttonRequest).setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        if(v.getId()==R.id.buttonRequest){
            dismiss();}
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}


