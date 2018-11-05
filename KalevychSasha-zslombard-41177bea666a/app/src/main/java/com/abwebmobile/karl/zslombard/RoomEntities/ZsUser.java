package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 08.02.2018.
 */
@Entity
public class ZsUser {
    public String login;
    public  String password;
    @PrimaryKey
    public long id;
    public String name;
    public  String phone;
    public  String mail;
    public  int age;
    public  String sex;
    public  String status;
    public long loginOfferNumber;
}
