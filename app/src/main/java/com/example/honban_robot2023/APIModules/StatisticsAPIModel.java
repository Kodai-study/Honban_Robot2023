package com.example.honban_robot2023.APIModules;

import android.util.Log;

import com.example.honban_robot2023.Models.CommonParameters;
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
    private float defectRate;


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

    public static final int COLUM_NUMBER = 15;

    public Date getFirstDateOfRange() {
        if (firstDateOfRangeString == null || firstDateOfRangeString.equals(""))
            return null;

        try {
            return CommonParameters.APISTRING_TO_DATETIME_FORMATTER.parse(firstDateOfRangeString);
        } catch (ParseException e) {
            Log.d("APIフェッチエラー", e.getMessage());
            return null;
        }
    }

    public Date getEndDateOfRange() {
        if (endDateOfRangeString == null || endDateOfRangeString.equals(""))
            return null;

        try {
            return CommonParameters.APISTRING_TO_DATETIME_FORMATTER.parse(endDateOfRangeString);
        } catch (ParseException e) {
            Log.d("APIフェッチエラー", e.getMessage());
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

    public float getDefectRate() {
        return defectRate;
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

    public StatisticsAPIModel(String firstDateOfRangeString, String endDateOfRangeString, int count_Scan, int count_Ok, int count_Ng, int ngCount_IC1, int ngCount_IC2, int ngCount_R5, int ngCount_R10, int ngCount_R11, int ngCount_R12, int ngCount_R18, int ngCount_DIPSW, int ngCount_Voltage, int ngCount_Frequency) {
        this.firstDateOfRangeString = firstDateOfRangeString;
        this.endDateOfRangeString = endDateOfRangeString;
        this.count_Scan = count_Scan;
        this.count_Ok = count_Ok;
        this.count_Ng = count_Ng;
        this.ngCount_IC1 = ngCount_IC1;
        this.ngCount_IC2 = ngCount_IC2;
        this.ngCount_R5 = ngCount_R5;
        this.ngCount_R10 = ngCount_R10;
        this.ngCount_R11 = ngCount_R11;
        this.ngCount_R12 = ngCount_R12;
        this.ngCount_R18 = ngCount_R18;
        this.ngCount_DIPSW = ngCount_DIPSW;
        this.ngCount_Voltage = ngCount_Voltage;
        this.ngCount_Frequency = ngCount_Frequency;
    }

    public int ngCount_VisualInspection() {
        return this.ngCount_IC1 +
                this.ngCount_IC2 +
                this.ngCount_R5 +
                this.ngCount_R10 +
                this.ngCount_R11 +
                this.ngCount_R12 +
                this.ngCount_R18 +
                this.ngCount_DIPSW;
    }

    public int ngCount_FunctionalInspection() {
        return this.ngCount_Voltage +
                this.ngCount_Frequency;
    }

}