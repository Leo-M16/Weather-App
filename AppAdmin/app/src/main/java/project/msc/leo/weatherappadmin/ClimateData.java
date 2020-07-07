package project.msc.leo.weatherappadmin;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("precipitation", precipitation);
        result.put("tempHigh", tempHigh);
        result.put("tempLow", tempLow);


        return result;
    }

}

