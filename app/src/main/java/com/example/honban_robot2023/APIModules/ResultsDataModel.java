package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultsDataModel {
  private  String startTime;


  private  Character allResult;
  private int workID;
  private  float temprature;
  private  float humidity;
  private  float brightness;
  private VisualInspectionResults result;

    @SerializedName(value = "cycleTime")
    private String cycleTime;

    public Date getStartTime() throws ParseException {
        if(startTime == null){
            return new Date();
        }
        final DateFormat startTimeDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return startTimeDateFormatter.parse(startTime);
    }

    public Character getAllResult() {
        return allResult;
    }

    public int getWorkID() {
        return workID;
    }

    public float getTemprature() {
        return temprature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getBrightness() {
        return brightness;
    }

    public VisualInspectionResults getResult() {
        return result;
    }

    public Date getCycleTime() throws ParseException {
        if(cycleTime == null){
            return new Date();
        }
        final DateFormat cycleTimeDateFormatter = new SimpleDateFormat( "HH:mm:ss");
        return cycleTimeDateFormatter.parse(cycleTime);
    }
}

class VisualInspectionResults{
    private  PartsInspection_IC ic;
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

class PartsInspection_IC{
   private Character allResult;
   private Character iC1_dir;
   private Character iC2_dir;
   private Character iC1_have;
   private Character iC2_have;

    public Character getAllResult() {
        return allResult;
    }

    public Character getiC1_dir() {
        return iC1_dir;
    }

    public Character getiC2_dir() {
        return iC2_dir;
    }

    public Character getiC1_have() {
        return iC1_have;
    }

    public Character getiC2_have() {
        return iC2_have;
    }
}

class PartsInspection_WORK{
    private Character allResult;
    private Character dir;
    private Character is_OK;
    public Character getAllResult() {
        return allResult;
    }
    public Character getDir() {
        return dir;
    }
    public Character getIs_OK() {
        return is_OK;
    }
}
class PartsInspection_REGISTER{
    private Character allResult;
    private Character r05;
    private Character r10;
    private Character r11;
    private Character r12;
    private Character r18;

    public Character getAllResult() {
        return allResult;
    }

    public Character getR05() {
        return r05;
    }

    public Character getR10() {
        return r10;
    }

    public Character getR11() {
        return r11;
    }

    public Character getR12() {
        return r12;
    }

    public Character getR18() {
        return r18;
    }
}
class PartsInspection_DIPSW{
    private Character allResult;
    private String pattern;

    public Character getAllResult() {
        return allResult;
    }

    public String getPattern() {
        return pattern;
    }
}
