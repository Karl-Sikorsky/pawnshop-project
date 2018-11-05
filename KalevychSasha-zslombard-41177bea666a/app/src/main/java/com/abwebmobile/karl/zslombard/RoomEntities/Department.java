package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 08.02.2018.
 */
@Entity
public class Department {
    @PrimaryKey(autoGenerate = true)
    public long department_id;
    public String department_adress;
    public double department_latitude;
    public double department_longitude;
    public String department_name;
    public String city_name;
    public String department_phone;
    public String department_protocol;
    public String department_EDRPOU;
    public  String department_description;
    public  String department_director;
    public  String department_workday;
    public  String department_workhours;

}
