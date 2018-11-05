package com.abwebmobile.karl.zslombard;

/**
 * Created by Karl on 04.02.2018.
 */

public class AdressItem {
   public double Latitude;
    public double Longitude;
    public  double id;
    public  String name;
    public  String description;
    public  String adress;
    public  String code;
    public  String director;
    public  String hours;
    public boolean isActive;

    public AdressItem() {

    }

    public AdressItem(String adress, String description) {
        this.adress = adress;
        this.description = description;
        this.isActive =false;
    }

    public AdressItem(double id, String name, String description, String adress, String code, String director, String hours) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.code = code;
        this.director = director;
        this.hours = hours;
    }
}
