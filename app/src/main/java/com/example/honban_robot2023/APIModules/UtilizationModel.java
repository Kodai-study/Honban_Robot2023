package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class UtilizationModel {

    /* "currentDate": "2023-02-06T00:00:00",
    "timeOfOperation": "01:02:00",
    "timeOfStopSum": "00:00:00",
    "timeOfSupplyPause": "00:01:00",
    "timeOfPause":*/

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
        if (currentDateString.equals("") || currentDateString == null)
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(currentDateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfOperation() {
        if (timeOfOperationString.equals("") || timeOfOperationString == null)
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(timeOfOperationString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfStopSum() {
        if (timeOfStopSumString == null || timeOfStopSumString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(timeOfStopSumString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfSupplyPause() {
        if (timeOfSupplyPauseString == null || timeOfSupplyPauseString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(timeOfSupplyPauseString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getTimeOfPause() {
        if (timeOfPauseString == null || timeOfPauseString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(timeOfPauseString);
        } catch (ParseException e) {
            return null;
        }
    }
}