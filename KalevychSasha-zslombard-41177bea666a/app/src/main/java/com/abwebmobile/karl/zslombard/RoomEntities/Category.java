package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 08.02.2018.
 */
@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    public long category_id;
    public String category_name;
}
