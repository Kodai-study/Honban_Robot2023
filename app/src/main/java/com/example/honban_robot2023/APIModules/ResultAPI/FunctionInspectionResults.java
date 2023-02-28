package com.example.honban_robot2023.APIModules.ResultAPI;

public class FunctionInspectionResults {
    private Character voltage_result;
    private Character frequency_result;
    private float voltage_value;
    private int frequency_value;

    public Character getVoltage_result() {
        return voltage_result;
    }

    public Character getFrequency_result() {
        return frequency_result;
    }

    public float getVoltage_value() {
        return voltage_value;
    }

    public int getFrequency_value() {
        return frequency_value;
    }
}