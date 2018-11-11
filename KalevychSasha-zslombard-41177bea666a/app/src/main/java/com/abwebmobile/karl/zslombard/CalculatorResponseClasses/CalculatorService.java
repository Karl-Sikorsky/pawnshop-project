package com.abwebmobile.karl.zslombard.CalculatorResponseClasses;


import android.util.Log;

import com.abwebmobile.karl.zslombard.Calculable;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Karl on 16.02.2018.
 * <p>
 * claas that using for fetch data from calculator server url
 */

public class CalculatorService {
    private Calculable mCalculable;
    private ServerCalculator mService;

    public CalculatorService(Calculable calculable) {
        this.mCalculable = calculable;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl() // Адрес сервера
                .addConverterFactory(GsonConverterFactory.create()) // говорим ретрофиту что для сериализации необходимо использовать GSON
                .build();
        mService = retrofit.create(ServerCalculator.class);
    }


    public void calculateData(int city, int cityAdress, int material, int materialContent, float weight, int clientType, int period) {
        Call<CalculatorResponse> call = mService.calculate("credit_calculate", city, cityAdress, material, materialContent, weight, clientType, period);
        call.enqueue(new Callback<CalculatorResponse>() {
            @Override
            public void onResponse(Call<CalculatorResponse> call, Response<CalculatorResponse> response) {
                if (response.isSuccessful()) {
                    // запрос выполнился успешно, сервер вернул Status 200
                    Log.d("loggedR", "all is ok");
                    Log.d("loggedR", response.body().getState().toString());
                    Log.d("loggedR", response.body().getData().getAddresses());
                    Log.d("calculator", "sucsess percent = " + response.body().getData().getResult().getCreditPercent());
                    mCalculable.onCalculate(response.body().getData().getResult().getCreditSumm(), response.body().getData().getResult().getCreditPercent(), response.body().getData().getResult().getCreditUsePercent(), response.body().getData().getResult().getCreditReturnSumm());

                } else {
                    // сервер вернул ошибку
                    Log.d("loggedR", "have error");
                    Log.d("loggedR", response.message());
                    Log.d("loggedR", response.errorBody().toString());
                    Log.d("calculator", "ERROR percent = " + response.body().getData().getResult().getCreditPercent());
                    mCalculable.onCalculate("error", "error", "error", "error");

                }
            }

            @Override
            public void onFailure(Call<CalculatorResponse> call, Throwable t) {
                // ошибка во время выполнения запроса
                Log.d("loggedR", "runtime failure");
                Log.d("loggedR", t.getMessage());
                Log.d("calculator", "FAILURE ");
                mCalculable.onCalculate("error", "error", "error", "error");
            }
        });

    }
}

