package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

public class ResultsDataModel {
    @SerializedName(value = "value")
    private int value;

    public int getValue() {
        return value;
    }

    public ResultsDataModel(int value) {
        this.value = value;
    }
}