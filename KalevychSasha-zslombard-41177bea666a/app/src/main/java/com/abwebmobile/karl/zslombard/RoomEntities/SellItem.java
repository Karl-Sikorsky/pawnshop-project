package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Karl on 07.02.2018.
 */
@Entity

public class SellItem {
    @PrimaryKey
    public long id;
    public long sellItem_code;
    public String name;
    public  String description;
    public String brand;
    public  long owner_id;

    public  float price;
    public  String price_presentation;
    public  String date;
    //public  String[] picture_urls;
    public  String adress;
    public  String city;
    public int state;
    public  String nomenclature;



    public SellItem() {
        this.name = "Телефон Samsung J5 500H";
        this.description = "В хорошому стані, був в експлуатації пів року, є захисне скло, чохол, зарядне та навушники в комплекті";
        this.price_presentation = "2300грн";
        this.adress = "вул. Харківське шоссе, 160";
        this.city = "м.Київ";
        this.nomenclature = "UAH";
        this.price = 2300;
        this.brand = "Samsung";
        this.state = 0;
    }
}
