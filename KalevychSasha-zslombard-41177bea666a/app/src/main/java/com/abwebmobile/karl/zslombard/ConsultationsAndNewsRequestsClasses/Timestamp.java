package com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Karl on 20.02.2018.
 */
@Entity
public class Timestamp {
    @PrimaryKey
    @SerializedName("lastUpdate")
    @Expose
    public long lastUpdate;


}
