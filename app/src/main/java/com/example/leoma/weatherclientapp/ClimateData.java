package com.example.leoma.weatherclientapp;


public class ClimateData {

    String date;
    String tempHigh;
    String tempLow;
    String precipitation;

    public ClimateData() {
    }


    public ClimateData(String date, String tempHigh, String tempLow, String precipitation) {
        this.date = date;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.precipitation = precipitation;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public String getTempLow() {
        return tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

}

