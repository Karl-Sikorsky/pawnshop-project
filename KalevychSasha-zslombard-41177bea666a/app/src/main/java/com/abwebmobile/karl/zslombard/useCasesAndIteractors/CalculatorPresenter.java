package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import android.util.Log;

import com.abwebmobile.karl.zslombard.Calculable;
import com.abwebmobile.karl.zslombard.CalculatorResponseClasses.CalculatorService;

/**
 * Created by Karl on 05.02.2018.
 */

public class CalculatorPresenter implements  Calculable{
    Calculable calculable;
    int days;
    int status;
    int silverGold;
    int city;

    public int getCityAdress() {
        return cityAdress;
    }

    public void setCityAdress(int cityAdress) {
        this.cityAdress = cityAdress;
        checkChanges();
    }

    int cityAdress;
    int content;
    float weight;
    CalculatorService service;
    public CalculatorPresenter(Calculable calculable) {
        this.calculable = calculable;
        days = 0;
        status = 0;
        silverGold = 0;
        city = 0;
        content = 0;
        weight = 0f;
         service = new CalculatorService(this);
    }

    public void setDays(int days) {
        this.days = days;
        checkChanges();
    }

    public void setStatus(int status) {
        this.status = status;
        checkChanges();
    }

    public void setSilverGold(int silverGold) {
        this.silverGold = silverGold;
        checkChanges();
    }

    public void setCity(int city) {
        this.city = city;
        checkChanges();
    }

    public void setContent(int content) {
        this.content = content;
        checkChanges();
    }

    public void setWeight(Float weight) {
        this.weight = weight;
        checkChanges();
    }

    private void checkChanges() {
        Log.d("calculator", "checking changes");
        service.calculateData(city, cityAdress, silverGold, content, weight, status, days);


    }

    @Override
    public void onCalculate(String sumCredit, String absPercent, String usagePercent, String toReturn) {
        Log.d("calculator", "in presenter onCalculate ");

        calculable.onCalculate(sumCredit,absPercent, usagePercent, toReturn);

    }
}
