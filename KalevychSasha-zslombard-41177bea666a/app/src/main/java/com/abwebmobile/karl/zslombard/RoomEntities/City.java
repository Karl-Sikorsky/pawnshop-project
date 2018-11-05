package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 08.02.2018.
 */
@Entity
public class City {
    @PrimaryKey(autoGenerate = true)
    public  long city_id;
    public String city_name;
    public double lat;
    public double lng;

    @Override
    public String toString() {
        return city_name;
    }
}
