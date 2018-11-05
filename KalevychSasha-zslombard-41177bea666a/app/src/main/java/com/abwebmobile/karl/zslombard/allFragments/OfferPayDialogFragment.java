package com.abwebmobile.karl.zslombard.allFragments;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.PayViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ua.privatbank.paylibliqpay.ErrorCode;
import ua.privatbank.paylibliqpay.LiqPay;
import ua.privatbank.paylibliqpay.LiqPayCallBack;

/**
 * Created by Karl on 17.02.2018.
 *
 * Диалог, отвечающий за отображение окна подтверждения оплаты
 */

public class OfferPayDialogFragment extends DialogFragment implements View.OnClickListener {
    PayViewModel mViewModel;
Float totalPay;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }
    TextView toPay;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("ЗАЛИШИТИ ЗАЯВКУ");
        View v = inflater.inflate(R.layout.dialog_pay_choosed, container, false);
        v.findViewById(R.id.buttonPay).setOnClickListener(this);
        TextView tvCB = (TextView)v.findViewById(R.id.textViewCreditBalance);
        tvCB.setText(mViewModel.getSumOfAllCreditBalances());
        TextView tvAccuredInterest = (TextView)v.findViewById(R.id.textViewAccuredInterest);
        tvAccuredInterest.setText(mViewModel.getSumOfAllAccuredInterests());
        TextView surcharge = (TextView)v.findViewById(R.id.textViewSurcharge);
        surcharge.setText(mViewModel.getSumOfAllSurcherges());
          toPay = (TextView)v.findViewById(R.id.textViewToPay);
          totalPay = mViewModel.getTotalPaySum();
        toPay.setText(String.format("%.2f", totalPay));
        return v;
        }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        if(v.getId()==R.id.buttonPay){
            initLiqPay();}
        }

    public static final int READ_PHONE_STATE_PERMISSION = 100;
    JSONObject object;

    private void initLiqPay() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_PERMISSION);
        }

        LiqPayCallBack callBack = new LiqPayCallBack() {
            @Override
            public void onResponseSuccess(final String resp) {
                object = null;
                try {
                    object = new JSONObject(resp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onResponceError(final ErrorCode errorCode) {

            }
        };
if(totalPay>0) {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("version", "3");
    map.put("public_key", PUBLIC_KEY);
    map.put("action", "pay");
    map.put("amount", String.valueOf(Math.ceil((double)totalPay)));
    map.put("currency", "UAH");
    map.put("description", "Сплата за кредитом");
    map.put("order_id", String.valueOf(Math.random() * 999999));
    map.put("language", "ru");
    map.put("server_url", );
    map.put("sandbox", "1");
    String privateKey = PRIVATE_KEY;
    LiqPay.checkout(getActivity().getApplicationContext(), map, privateKey, callBack);
}else{
    Toast.makeText(getActivity().getApplicationContext(), "ВИ НЕ ОБРАЛИ КРЕДИТІВ ДО ОПЛАТИ", Toast.LENGTH_SHORT).show();
}

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case READ_PHONE_STATE_PERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("liqPayResponse", "permission granted ");
                }else {
                    Log.d("liqPayResponse", "permission denied ");
                }
            }
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
