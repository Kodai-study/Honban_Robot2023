package com.example.honban_robot2023.APIModules;


public class StationStateAPIModel {

    private boolean isSuccessConnect;
    private int numberOfWork_VisualStation;
    private int numberOfWork_FunctionalStation;
    private int numberOfWork_AssemblyStation;
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

    public int getNumberOfWork_VisualStation() {
        return numberOfWork_VisualStation;
    }

    public int getNumberOfWork_FunctionalStation() {
        return numberOfWork_FunctionalStation;
    }

    public int getNumberOfWork_AssemblyStation() {
        return numberOfWork_AssemblyStation;
    }

    public boolean isSuccessConnect() {
        return isSuccessConnect;
    }
    public int getNumberOfOKStock() {
        return numberOfOKStock;
    }

    public int getNumberOfNGStock() {
        return numberOfNGStock;
    }

    public String getSystemState() {
        return systemState;
    }

    public String getStationState_Supply() {
        return stationState_Supply;
    }

    public String getStationState_Visual() {
        return stationState_Visual;
    }

    public String getStationState_Function() {
        return stationState_Function;
    }

    public String getStationState_Assembly() {
        return stationState_Assembly;
    }

    public boolean isSystemPause() {
        return isSystemPause;
    }

    public String getSystemPauseCause() {
        return systemPauseCause;
    }

    public boolean isInspectedJustBefore() {
        return isInspectedJustBefore;
    }

    public int getResultFrequency() {
        return resultFrequency;
    }

    public float getResultVoltage() {
        return resultVoltage;
    }

    public String getVisualInspectionData() {
        return visualInspectionData;
    }
}
