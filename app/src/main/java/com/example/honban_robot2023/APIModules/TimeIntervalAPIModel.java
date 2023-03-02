package com.example.honban_robot2023.APIModules;

import android.util.Log;

import com.example.honban_robot2023.Models.CommonParameters;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * ワークごとの、工程が変化した時間、タイムスタンプを表すデータクラス
 * <a href="https://192.168.96.69:7015/api/times">自作API</a>
 * "https://192.168.96.69:7015/api/times"
 * にアクセスしてデータを取得する
 */
public class TimeIntervalAPIModel {
   public static final int COLUM_NUMBER = 6;
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
         return CommonParameters.APISTRING_TO_DATETIME_FORMATTER.parse(startTimeString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー", e.getMessage());
         return null;
      }
   }

   public Date getTime_supply() {
      if (time_supplyString == null || time_supplyString.equals(""))
         return null;

      try {
         return CommonParameters.TIMEONLY_FORMATTER.parse(time_supplyString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー", e.getMessage());
         return null;
      }
   }

   public Date getTime_visualStation() {
      if (time_visualStationString == null || time_visualStationString.equals(""))
         return null;

      try {
         return CommonParameters.TIMEONLY_FORMATTER.parse(time_visualStationString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー", e.getMessage());
         return null;
      }
   }

   public Date getTime_functionalStation() {
      if (time_functionalStationString == null || time_functionalStationString.equals(""))
         return null;

      try {
         return CommonParameters.TIMEONLY_FORMATTER.parse(time_functionalStationString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー", e.getMessage());
         return null;
      }
   }

   public Date getTime_assemblyStation() {
      if (time_assemblyStationString == null || time_assemblyStationString.equals(""))
         return null;

      try {
         return CommonParameters.TIMEONLY_FORMATTER.parse(time_assemblyStationString);
      } catch (Exception e) {
         Log.d("APIフェッチエラー", e.getMessage());
         return null;
      }
   }

   public TimeIntervalAPIModel(int cycleID, String startTimeString, String time_supplyString, String time_visualStationString, String time_functionalStationString, String time_assemblyStationString) {
      this.cycleID = cycleID;
      this.startTimeString = startTimeString;
      this.time_supplyString = time_supplyString;
      this.time_visualStationString = time_visualStationString;
      this.time_functionalStationString = time_functionalStationString;
      this.time_assemblyStationString = time_assemblyStationString;
   }

}