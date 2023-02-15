package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class UtilizationModel {

    @SerializedName(value = "currentDate")
    private String currentDateString;

    @SerializedName(value = "timeOfOperation")
    private String timeOfOperationString;

    @SerializedName(value = "timeOfStopSum")
    private String timeOfStopSumString;

    @SerializedName(value = "timeOfSupplyPause")
    private String timeOfSupplyPauseString;

    @SerializedName(value = "timeOfPause")
    private String timeOfPauseString;


    public Date getCurrentDate() {
        if (currentDateString == null || currentDateString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(currentDateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfOperation() {
        if (timeOfOperationString == null || timeOfOperationString.equals(""))
            return null;

        try {
            return ConfigParameters.TIMEONLY_FORMATTER.parse(timeOfOperationString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfStopSum() {
        if (timeOfStopSumString == null || timeOfStopSumString.equals(""))
            return null;

        try {
            return ConfigParameters.TIMEONLY_FORMATTER.parse(timeOfStopSumString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfSupplyPause() {
        if (timeOfSupplyPauseString == null || timeOfSupplyPauseString.equals(""))
            return null;

        try {
            return ConfigParameters.TIMEONLY_FORMATTER.parse(timeOfSupplyPauseString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfPause() {
        if (timeOfPauseString == null || timeOfPauseString.equals(""))
            return null;

        try {
            return ConfigParameters.TIMEONLY_FORMATTER.parse(timeOfPauseString);
        } catch (ParseException e) {
            return null;
        }
    }
}