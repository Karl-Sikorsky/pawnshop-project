package com.abwebmobile.karl.zslombard;

import com.abwebmobile.karl.zslombard.RoomEntities.Offer;

/**
 * Created by Karl on 17.02.2018.
 */

public interface ButtonIncreaser {
    public void increaseButton(int requestedCount, Offer offerAddToPay, Float aFloat);

    void decreaseButton(int countCheckedOffers, Offer offer, Float aFloat);
}
