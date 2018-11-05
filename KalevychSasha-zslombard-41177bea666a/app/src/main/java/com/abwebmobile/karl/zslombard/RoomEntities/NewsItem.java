package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 08.02.2018.
 */
@Entity
public class NewsItem {
    @PrimaryKey
    public long news_id;
    public String news_name;
    public String news_description;
    public String image_url;
}
