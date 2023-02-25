package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

public class StationStateAPIModel {

    private int numberOfOKStock;
    private int numberOfNGStock;
    private String systemState;
    private String stationState_Supply;
    private String stationState_Visual;
    private String stationState_Function;
    private String stationState_Assembly;
    private boolean isSystemPause;
    private String systemPauseCause;
    private boolean isInspectedJustBefore;
    private int resultFrequency;
    private float resultVoltage;
    private String visualInspectionData;
}
