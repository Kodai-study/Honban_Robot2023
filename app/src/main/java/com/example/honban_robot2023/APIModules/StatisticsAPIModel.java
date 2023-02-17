package com.example.honban_robot2023.APIModules;

import android.util.Log;

import com.example.honban_robot2023.Models.ConfigParameters;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class StatisticsAPIModel {

    @SerializedName(value = "firstDateOfRange")
    private String firstDateOfRangeString;

    @SerializedName(value = "endDateOfRange")
    private String endDateOfRangeString;

    private int count_Scan;
    private int count_Ok;
    private int count_Ng;
    private int ngCount_IC1;
    private int ngCount_IC2;
    private int ngCount_R5;
    private int ngCount_R10;
    private int ngCount_R11;
    private int ngCount_R12;
    private int ngCount_R18;
    private int ngCount_DIPSW;
    private int ngCount_Voltage;
    private int ngCount_Frequency;

    public static final int COLUM_NUMBER = 14;

    public Date getFirstDateOfRange() {
        if (firstDateOfRangeString == null || firstDateOfRangeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(firstDateOfRangeString);
        } catch (ParseException e) {
            Log.d("APIフェッチエラー",e.getMessage());
            return null;
        }
    }

    public Date getEndDateOfRange() {
        if (endDateOfRangeString == null || endDateOfRangeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(endDateOfRangeString);
        } catch (ParseException e) {
            Log.d("APIフェッチエラー",e.getMessage());
            return null;
        }
    }

    public int getCount_Scan() {
        return count_Scan;
    }

    public int getCount_Ok() {
        return count_Ok;
    }

    public int getCount_Ng() {
        return count_Ng;
    }

    public int getNgCount_IC1() {
        return ngCount_IC1;
    }

    public int getNgCount_IC2() {
        return ngCount_IC2;
    }

    public int getNgCount_R5() {
        return ngCount_R5;
    }

    public int getNgCount_R10() {
        return ngCount_R10;
    }

    public int getNgCount_R11() {
        return ngCount_R11;
    }

    public int getNgCount_R12() {
        return ngCount_R12;
    }

    public int getNgCount_R18() {
        return ngCount_R18;
    }

    public int getNgCount_DIPSW() {
        return ngCount_DIPSW;
    }

    public int getNgCount_Voltage() {
        return ngCount_Voltage;
    }

    public int getNgCount_Frequency() {
        return ngCount_Frequency;
    }
}