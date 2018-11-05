package com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses;

/**
 * Created by Karl on 20.02.2018.
 */

public interface TimestampChecker {
    void onNeedUpdate(long lastUpdate);

    void onBadResponse();
}
