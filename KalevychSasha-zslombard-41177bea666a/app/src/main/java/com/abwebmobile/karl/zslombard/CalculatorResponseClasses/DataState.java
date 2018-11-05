
package com.abwebmobile.karl.zslombard.CalculatorResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//object that using for parsing JSON in calculator module
public class DataState {

    @SerializedName("city")
    @Expose
    private Integer city;
    @SerializedName("city_address")
    @Expose
    private Integer cityAddress;
    @SerializedName("material")
    @Expose
    private Integer material;
    @SerializedName("material_content")
    @Expose
    private Integer materialContent;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("client_type")
    @Expose
    private Integer clientType;

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(Integer cityAddress) {
        this.cityAddress = cityAddress;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public Integer getMaterialContent() {
        return materialContent;
    }

    public void setMaterialContent(Integer materialContent) {
        this.materialContent = materialContent;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

}
