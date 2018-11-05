package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 08.02.2018.
 */
@Entity
public class AboutItem {
    @PrimaryKey
    public long item_id;
    public String item_name;
    public String item_description;
    public int item_place;
}
