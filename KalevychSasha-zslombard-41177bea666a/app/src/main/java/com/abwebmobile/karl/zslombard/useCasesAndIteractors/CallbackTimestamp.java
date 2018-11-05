package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses.Timestamp;

/**
 * Created by Karl on 20.02.2018.
 */

interface CallbackTimestamp<T> {
    void onLoad (Timestamp timestamp);
}
