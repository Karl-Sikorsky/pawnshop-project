
package com.abwebmobile.karl.zslombard.CalculatorResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//object that using for parsing JSON in calculator module
public class Result {

    @SerializedName("credit_summ")
    @Expose
    private String creditSumm;
    @SerializedName("credit_percent")
    @Expose
    private String creditPercent;
    @SerializedName("credit_use_percent")
    @Expose
    private String creditUsePercent;
    @SerializedName("credit_return_summ")
    @Expose
    private String creditReturnSumm;

    public String getCreditSumm() {
        return creditSumm;
    }

    public void setCreditSumm(String creditSumm) {
        this.creditSumm = creditSumm;
    }

    public String getCreditPercent() {
        return creditPercent;
    }

    public void setCreditPercent(String creditPercent) {
        this.creditPercent = creditPercent;
    }

    public String getCreditUsePercent() {
        return creditUsePercent;
    }

    public void setCreditUsePercent(String creditUsePercent) {
        this.creditUsePercent = creditUsePercent;
    }

    public String getCreditReturnSumm() {
        return creditReturnSumm;
    }

    public void setCreditReturnSumm(String creditReturnSumm) {
        this.creditReturnSumm = creditReturnSumm;
    }

}
