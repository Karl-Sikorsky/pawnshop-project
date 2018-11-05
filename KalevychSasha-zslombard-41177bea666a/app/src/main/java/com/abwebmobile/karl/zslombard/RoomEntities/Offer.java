package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 14.02.2018.
 */
@Entity
public class Offer {
    @PrimaryKey
   public long contractNumber;
    public  String agreementDate;
    public  String description;
    public  String lastOperationDate;
    public String expirationDate;
    public int daysTheEnd;
    public int daysOverdue;
    public  double interestRatePerDay;
    public double maximumCredit;
    public double creditBalance;
    public  double accuredInterests;
    public  double surcharge;
    public double minimumAmountPayment;
    public double maximumAmountPayment;
    public double matching;

}
