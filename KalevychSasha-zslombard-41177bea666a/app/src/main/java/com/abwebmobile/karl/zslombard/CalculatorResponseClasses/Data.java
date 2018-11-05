
package com.abwebmobile.karl.zslombard.CalculatorResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//object that using for parsing JSON in calculator module
public class Data {

    @SerializedName("data_state")
    @Expose
    private DataState dataState;
    @SerializedName("addresses")
    @Expose
    private String addresses;
    @SerializedName("material_content")
    @Expose
    private String materialContent;
    @SerializedName("client_types")
    @Expose
    private String clientTypes;
    @SerializedName("result")
    @Expose
    private Result result;

    public DataState getDataState() {
        return dataState;
    }

    public void setDataState(DataState dataState) {
        this.dataState = dataState;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getMaterialContent() {
        return materialContent;
    }

    public void setMaterialContent(String materialContent) {
        this.materialContent = materialContent;
    }

    public String getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(String clientTypes) {
        this.clientTypes = clientTypes;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
