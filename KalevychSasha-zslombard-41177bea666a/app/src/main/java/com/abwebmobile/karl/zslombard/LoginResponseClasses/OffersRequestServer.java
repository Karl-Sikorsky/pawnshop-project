package com.abwebmobile.karl.zslombard.LoginResponseClasses;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karl on 15.02.2018.
 */

public interface OffersRequestServer {
    @FormUrlEncoded
    @POST("/profile_ajax.php")
    Call<Date> requestOffers(@Field("act") String act,
                                          @Field("number") String number,
                                          @Field("login") String login,
                                          @Field("pass") String pass

    );
}
