package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import com.abwebmobile.karl.zslombard.RoomEntities.NewsItem;

import java.util.List;

/**
 * Created by Karl on 13.02.2018.
 */

interface CallbackNews<T> {
    void onLoad(List<NewsItem> items);
}
