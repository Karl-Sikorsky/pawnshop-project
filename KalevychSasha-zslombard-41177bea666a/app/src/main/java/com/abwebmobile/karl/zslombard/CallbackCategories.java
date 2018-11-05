package com.abwebmobile.karl.zslombard;

import com.abwebmobile.karl.zslombard.RoomEntities.Category;

import java.util.List;

/**
 * Created by Karl on 08.02.2018.
 */

public interface CallbackCategories<T> {
    void onLoad(List<Category> loaded);
}
