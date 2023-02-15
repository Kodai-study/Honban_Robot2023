package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

public class TimeStumpModel {
    private int workId;

    @SerializedName(value = "startTime")
    private String supplyTimeString;

    @SerializedName(value = "startTime")
    private String visal_inTimeString;

    @SerializedName(value = "startTime")
    private String functional_inTimeString;

    @SerializedName(value = "startTime")
    private String assembly_inTimeString;

    @SerializedName(value = "startTime")
    private String assemblyTimeString;

    public int getWorkId() {

        return workId;
    }

    public String getSupply() {
        return supply;
    }

    public String getVisal_in() {
        return visal_in;
    }

    public String getFunctional_in() {
        return functional_in;
    }

    public String getAssembly_in() {
        return assembly_in;
    }

    public String getAssembly() {
        return assembly;
    }
}
