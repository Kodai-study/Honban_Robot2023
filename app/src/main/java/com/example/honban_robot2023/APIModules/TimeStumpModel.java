package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class TimeStumpModel {
    private int workId;

    @SerializedName(value = "startTime")
    private String supplyTimeString;

    @SerializedName(value = "startTime")
    private String visual_inTimeString;

    @SerializedName(value = "startTime")
    private String functional_inTimeString;

    @SerializedName(value = "startTime")
    private String assembly_inTimeString;

    @SerializedName(value = "startTime")
    private String assemblyTimeString;

    public int getWorkId() {

        return workId;
    }

    public Date getSupply() {
        if (supplyTimeString == null || supplyTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(supplyTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getVisual_in() {
        if (visual_inTimeString == null || visual_inTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(visual_inTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getFunctional_in() {
        if (functional_inTimeString == null || functional_inTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(functional_inTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getAssembly_in() {
        if (assembly_inTimeString == null || assembly_inTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(assembly_inTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getAssembly() {
        if (assemblyTimeString == null || assemblyTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.DATETIME_FORMATTER.parse(assemblyTimeString);
        } catch (ParseException e) {
            return null;
        }
    }
}