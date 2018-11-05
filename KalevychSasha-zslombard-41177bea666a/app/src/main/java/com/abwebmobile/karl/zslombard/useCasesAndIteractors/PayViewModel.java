package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.DatabaseProvider;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;

import java.util.ArrayList;

/**
 * Created by Karl on 17.02.2018.
 */

public class PayViewModel  extends AndroidViewModel {

    Context context;


    public PayViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

        DatabaseProvider.getInstance().createDatabaseReference(context);
    }

    ArrayList<Offer> offersToPay = new ArrayList<>();


    public void addOfferToPay(Offer offerAddToPay, Float aFloat) {

        Log.d("offersPay", "add offer with cb = "+ offerAddToPay.creditBalance);
        DatabaseProvider.getInstance().addOfferToPay(offerAddToPay);
        Log.d("offersPay", "now count of offers =  "+ offersToPay.size());
        DatabaseProvider.getInstance().changePaySum( aFloat);
        Log.d("float_control", "now sum = "+DatabaseProvider.getInstance().getTotalPaySum());
    }

    public String getSumOfAllCreditBalances() {
        offersToPay = DatabaseProvider.getInstance().getAllOffersToPay();
        Log.d("offersPay", "but now count of offers =  "+ offersToPay.size());
        float sum = 0f;
        for (int i=0;i<offersToPay.size();i++){
            sum+=offersToPay.get(i).creditBalance;
        }
        return String.valueOf(sum);
    }

    public String getSumOfAllAccuredInterests() {
        offersToPay = DatabaseProvider.getInstance().getAllOffersToPay();
        float sum = 0f;
        for (int i=0;i<offersToPay.size();i++){
            sum+=offersToPay.get(i).accuredInterests;
        }
        return String.valueOf(sum);
    }

    public String getSumOfAllSurcherges() {
        offersToPay = DatabaseProvider.getInstance().getAllOffersToPay();
        float sum = 0f;
        for (int i=0;i<offersToPay.size();i++){
            sum+=offersToPay.get(i).surcharge;
        }
        return String.valueOf(sum);
    }



    public Float getTotalPaySum() {
        Float totalPaySum = DatabaseProvider.getInstance().getTotalPaySum();
        return totalPaySum;
    }

    public void removeOfferToPay(Offer offer, Float aFloat) {
        Log.d("offersPay", "add offer with cb = "+ offer.creditBalance);
        DatabaseProvider.getInstance().removeOfferToPay(offer);
        Log.d("offersPay", "now count of offers =  "+ offersToPay.size());
        DatabaseProvider.getInstance().changePaySum( -aFloat);
        Log.d("float_control", "now sum = "+DatabaseProvider.getInstance().getTotalPaySum());
    }

    public void removeAllOfferToPay() {
        DatabaseProvider.getInstance().changePaySum( -DatabaseProvider.getInstance().getTotalPaySum());

        DatabaseProvider.getInstance().removeAllOffersToPay();
    }
}
