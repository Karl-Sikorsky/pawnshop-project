package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import com.abwebmobile.karl.zslombard.RoomEntities.City;

import java.util.List;

/**
 * Created by Karl on 13.02.2018.
 */

interface CallbackCities<T> {
    void onLoad(List<City> items);
}
