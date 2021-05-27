package com.nicebank.clearing.model;

public class BankInfo {

    //ID,Bank Name,Type,City,State,Zip code
    Integer bankId;
    String bankName;
    String bankType;
    String cityName;
    String stateCode;
    String zipCode;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "BankInfo{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", bankType='" + bankType + '\'' +
                ", cityName='" + cityName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
