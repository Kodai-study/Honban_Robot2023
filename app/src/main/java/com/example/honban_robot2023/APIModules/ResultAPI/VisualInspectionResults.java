package com.example.honban_robot2023.APIModules.ResultAPI;

public class VisualInspectionResults {
    private PartsInspection_IC ic;
    private PartsInspection_WORK work;
    private PartsInspection_REGISTER r;
    private PartsInspection_DIPSW dipSw;

    public PartsInspection_IC getIc() {
        return ic;
    }

    public PartsInspection_WORK getWork() {
        return work;
    }

    public PartsInspection_REGISTER getR() {
        return r;
    }

    public PartsInspection_DIPSW getDipSw() {
        return dipSw;
    }
}
