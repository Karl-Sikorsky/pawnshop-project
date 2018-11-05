package com.abwebmobile.karl.zslombard.CalculatorResponseClasses;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karl on 13.02.2018.
 * interface for retrofit, declared POST method
 */

public interface ServerCalculator {
    @FormUrlEncoded
    @POST("/ajax.php")
    Call<CalculatorResponse> calculate(@Field("act") String act,
                                       @Field("city") Integer city,
                                       @Field("city_adress") Integer city_adress,
                                       @Field("material") Integer material,
                                       @Field("material_content") Integer material_content,
                                       @Field("weight") float weight,
                                       @Field("client_type") Integer client_type,
                                       @Field("period") Integer period
    );
}
