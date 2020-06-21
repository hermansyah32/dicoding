package com.manheadblog.moviemania.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-mm-dd";
    private static final String DEFAULT_TIME_FORMAT = "yyyy-mm-dd";
    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-mm-dd HH:mm:ss";

    public static String getDateToday(){
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return formatter.format(new Date());
    }

    public static String getDateToday(SimpleDateFormat formatter){
        return formatter.format(new Date());
    }
}
