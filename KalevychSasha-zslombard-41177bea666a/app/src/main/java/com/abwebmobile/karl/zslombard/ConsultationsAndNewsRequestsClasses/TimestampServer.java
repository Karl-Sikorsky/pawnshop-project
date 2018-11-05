package com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Karl on 20.02.2018.
 */

public interface TimestampServer {

    @GET("/timestamp")
    Call<Timestamp> getUpdateTimestamp();

}
