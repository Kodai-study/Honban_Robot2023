package com.example.honban_robot2023.APIModules;

import com.example.honban_robot2023.Models.ConfigParameters;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class TimeStampModel {
    private int workId;


    @SerializedName(value = "supply")
    private String supplyTimeString;

    @SerializedName(value = "visal_in")
    private String visual_inTimeString;

    @SerializedName(value = "functional_in")
    private String functional_inTimeString;

    @SerializedName(value = "assembly_in")
    private String assembly_inTimeString;

    @SerializedName(value = "assembly")
    private String assemblyTimeString;

    public static final int COLUM_NUMBER = 6;

    public int getWorkId() {
        return workId;
    }

    public Date getSupply() {
        if (supplyTimeString == null || supplyTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(supplyTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getVisual_in() {
        if (visual_inTimeString == null || visual_inTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(visual_inTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getFunctional_in() {
        if (functional_inTimeString == null || functional_inTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(functional_inTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getAssembly_in() {
        if (assembly_inTimeString == null || assembly_inTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(assembly_inTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getAssembly() {
        if (assemblyTimeString == null || assemblyTimeString.equals(""))
            return null;

        try {
            return ConfigParameters.APISTRING_TO_DATETIME_FORMATTER.parse(assemblyTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public TimeStampModel(int workId, String supplyTimeString, String visual_inTimeString, String functional_inTimeString, String assembly_inTimeString, String assemblyTimeString) {
        this.workId = workId;
        this.supplyTimeString = supplyTimeString;
        this.visual_inTimeString = visual_inTimeString;
        this.functional_inTimeString = functional_inTimeString;
        this.assembly_inTimeString = assembly_inTimeString;
        this.assemblyTimeString = assemblyTimeString;
    }
}