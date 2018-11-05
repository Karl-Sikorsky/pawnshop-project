package com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Karl on 20.02.2018.
 */

public class TimestampService {

    private TimestampServer mService;
    private TimestampChecker mTimestampChecker;
    public TimestampService(TimestampChecker timestampChecker){
         this.mTimestampChecker = timestampChecker;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-8de5d-karlsikorsky.apiary-mock.com") // Адрес сервера
                .addConverterFactory(GsonConverterFactory.create()) // говорим ретрофиту что для сериализации необходимо использовать GSON
                .build();
        mService = retrofit.create(TimestampServer.class);
    }


    public void callForLastUpdate(){
        Log.d("update", "start retrofit");
        Call<Timestamp> call = mService.getUpdateTimestamp();
        call.enqueue(new Callback<Timestamp>() {
            @Override
            public void onResponse(Call<Timestamp> call, Response<Timestamp> response) {
                if (response.isSuccessful()) {
                    // запрос выполнился успешно, сервер вернул Status 200
                    Log.d("loggedR", "all is ok");
                    Log.d("loggedR", String.valueOf(response.body().lastUpdate));
                    Log.d("update", "have response from retrofit");
                    mTimestampChecker.onNeedUpdate(response.body().lastUpdate);

                } else {
                    // сервер вернул ошибку
                    Log.d("loggedR", "have error");
                    Log.d("loggedR", response.message());
                    Log.d("loggedR", response.errorBody().toString());
                    mTimestampChecker.onBadResponse();
                }

            }

            @Override
            public void onFailure(Call<Timestamp> call, Throwable t) {
                // ошибка во время выполнения запроса
                Log.d("loggedR", "runtime failure");
                Log.d("loggedR", t.getMessage());
                Log.d("timestamp", "FAILURE ");
                mTimestampChecker.onBadResponse();
            }
        });

    }
}



