package com.example.honban_robot2023.Models;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 全てのクラスで共通するパラメータをまとめたクラス
 */
public class CommonParameters {
   /**
    * APIから取得した時刻データを {@link java.util.Date} データに変換するフォーマッタ
    */
   public static final SimpleDateFormat APISTRING_TO_DATETIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

   /**
    * {@link java.util.Date} から、時刻データの文字列を作成させるフォーマッタ
    */
   public static final SimpleDateFormat TIMEONLY_FORMATTER = new SimpleDateFormat("HH:mm:ss", Locale.US);

   /**
    * {@link java.util.Date} から、年が下2桁の日付のみの文字列を作成させるフォーマッタ
    */
   public static final SimpleDateFormat DATEFORMATTER_YEAR_TWODIGITS = new SimpleDateFormat("yy/MM/dd", Locale.US);

   /**
    * {@link java.util.Date} から、日付のみの文字列を作成させるフォーマッタ
    */
   public static final SimpleDateFormat DATEONLY_FORMATTER = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

   /**
    * 日付、時刻のデータの文字列を作成させるフォーマッタ
    */
   public static final SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);

   /**
    * データがNULLだった時にテーブルに表示させる文字列
    */
   public static final String TABLEDATA_NOTHING = "-";

   /**
    * APIが取得できない時に、固定のテストデータを代わりに使う
    */
   public static final boolean IS_API_DEBUG_MODE = false;

}