package com.example.honban_robot2023.APIModules.ResultAPI;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultsDataModel {

    @SerializedName(value = "startTime")
    private String startTimeString;

    private Character allResult;
    private int workID;
    private float temprature;
    private float humidity;
    private float brightness;
    private VisualInspectionResults result;

    @SerializedName(value = "cycleTime")
    private String cycleTimeString;

    public Date getStartTime(){
        if (startTimeString == null) {
            return new Date();
        }
        try {
            final DateFormat startTimeDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return startTimeDateFormatter.parse(startTimeString);
        }catch (ParseException parseException){
            return null;
        }
    }

    public Character getAllResult() {
        return allResult;
    }

    public int getWorkID() {
        return workID;
    }

    public float getTemprature() {
        return temprature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getBrightness() {
        return brightness;
    }

    public VisualInspectionResults getResult() {
        return result;
    }

    public Date getCycleTime() throws ParseException {
        if (cycleTimeString == null) {
            return new Date();
        }
        final DateFormat cycleTimeDateFormatter = new SimpleDateFormat("HH:mm:ss");
        return cycleTimeDateFormatter.parse(cycleTimeString);
    }
}

