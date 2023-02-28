package com.example.honban_robot2023.APIModules;

public class InspectionTotalAPIModel {
    private int count_Scan;
    private int count_OK;
    private int count_NG;
    private int count_VisualInspectionNG;
    private int count_FunctionalInspectionNG;
    private int count_FrequencyNG;
    private int count_VoltageNG;
    private int count_VoltAndFreqNG;

    public int getCount_Scan() {
        return count_Scan;
    }

    public int getCount_OK() {
        return count_OK;
    }

    public int getCount_NG() {
        return count_NG;
    }

    public int getCount_VisualInspectionNG() {
        return count_VisualInspectionNG;
    }

    public int getCount_FunctionalInspectionNG() {
        return count_FunctionalInspectionNG;
    }

    public int getCount_FrequencyNG() {
        return count_FrequencyNG;
    }

    public int getCount_VoltageNG() {
        return count_VoltageNG;
    }

    public int getCount_VoltAndFreqNG() {
        return count_VoltAndFreqNG;
    }
}
