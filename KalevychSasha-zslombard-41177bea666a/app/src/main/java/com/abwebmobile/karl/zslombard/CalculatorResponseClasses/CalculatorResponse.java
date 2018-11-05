
package com.abwebmobile.karl.zslombard.CalculatorResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//object that using for parsing JSON in calculator module
public class CalculatorResponse {

    @SerializedName("state")
    @Expose
    private Boolean state;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
