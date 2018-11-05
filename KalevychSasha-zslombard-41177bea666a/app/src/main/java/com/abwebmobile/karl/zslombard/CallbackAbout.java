package com.abwebmobile.karl.zslombard;

import com.abwebmobile.karl.zslombard.RoomEntities.AboutItem;

import java.util.List;

/**
 * Created by Karl on 08.02.2018.
 */

public interface CallbackAbout<T> {
    void onLoad(List<AboutItem> loaded);
}
