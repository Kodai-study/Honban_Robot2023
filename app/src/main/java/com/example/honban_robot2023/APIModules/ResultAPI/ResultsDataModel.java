package com.example.honban_robot2023.APIModules.ResultAPI;

import com.example.honban_robot2023.Models.CommonParameters;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class ResultsDataModel {

    @SerializedName(value = "startTime")
    private String startTimeString;

    private Character allResult;
    private int workID;

    @SerializedName(value = "temprature")
    private float temperature;
    private float humidity;
    private float brightness;
    private VisualInspectionResults result_visualInspection;
    private FunctionInspectionResults result_functionalInspection;

    @SerializedName(value = "cycleTime")
    private String cycleTimeString;


    public static final int COLUM_NUMBER = 24;

    public Date getStartTime() {
        if (startTimeString == null) {
            return new Date();
        }
        try {
            return CommonParameters.APISTRING_TO_DATETIME_FORMATTER.parse(startTimeString);
        } catch (ParseException parseException) {
            return null;
        }
    }

    public Character getAllResult() {
        return allResult;
    }

    public int getWorkID() {
        return workID;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getBrightness() {
        return brightness;
    }

    public VisualInspectionResults getResult_visualInspection() {
        return result_visualInspection;
    }

    public FunctionInspectionResults getResult_functionalInspection() {
        return result_functionalInspection;
    }

    public Date getCycleTime() {
        if (cycleTimeString == null) {
            return null;
        }
        try {
            return CommonParameters.TIMEONLY_FORMATTER.parse(cycleTimeString);
        } catch (ParseException parseException) {
            return null;
        }
    }

}