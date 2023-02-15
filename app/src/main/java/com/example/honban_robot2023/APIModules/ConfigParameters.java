package com.example.honban_robot2023.APIModules;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ConfigParameters {
   public static final SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.US);
   public static final SimpleDateFormat TIMEONLY_FORMATTER = new SimpleDateFormat("HH:mm:ss", Locale.US);
   public static final SimpleDateFormat DATEFORMATTER_YEAR_TWODIGITS = new SimpleDateFormat("yy/MM/dd",Locale.US);

   public static final SimpleDateFormat DATEONLY_FORMATTER = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
}
