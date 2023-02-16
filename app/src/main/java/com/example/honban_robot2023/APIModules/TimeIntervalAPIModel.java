package com.example.honban_robot2023.APIModules;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.Date;

public class TimeIntervalAPIModel {
   private int cycleID;

   @SerializedName(value = "startTime")
   private String startTimeString;

   @SerializedName(value = "time_supply")
   private String time_supplyString;

   @SerializedName(value = "time_visualStation")
   private String time_visualStationString;

   @SerializedName(value = "time_functionalStation")
   private String time_functionalStationString;

   @SerializedName(value = "time_assemblyStation")
   private String time_assemblyStationString;

   public int getCycleID() {
      return cycleID;
   }

   public Date getStartTime() {
      if (startTimeString == null || startTimeString.equals(""))
         return null;

      try {
         return ConfigParameters.TIMEONLY_FORMATTER.parse(startTimeString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー",e.getMessage());
         return null;
      }
   }

   public Date getTime_supply() {
      if (time_supplyString == null || time_supplyString.equals(""))
         return null;

      try {
         return ConfigParameters.TIMEONLY_FORMATTER.parse(time_supplyString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー",e.getMessage());
         return null;
      }
   }

   public Date getTime_visualStation() {
      if (time_visualStationString == null || time_visualStationString.equals(""))
         return null;

      try {
         return ConfigParameters.TIMEONLY_FORMATTER.parse(time_visualStationString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー",e.getMessage());
         return null;
      }
   }

   public Date getTime_functionalStation() {
      if (time_functionalStationString == null || time_functionalStationString.equals(""))
         return null;

      try {
         return ConfigParameters.TIMEONLY_FORMATTER.parse(time_functionalStationString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー",e.getMessage());
         return null;
      }
   }

   public Date getTime_assemblyStation() {
      if (time_assemblyStationString == null || time_assemblyStationString.equals(""))
         return null;

      try {
         return ConfigParameters.TIMEONLY_FORMATTER.parse(time_assemblyStationString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー",e.getMessage());
         return null;
      }
   }
}

