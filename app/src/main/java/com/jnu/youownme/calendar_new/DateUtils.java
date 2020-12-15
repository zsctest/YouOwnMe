package com.jnu.youownme.calendar_new;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DateUtils extends android.text.format.DateUtils {
    private static int[] longMonth = {1,3,5,7,8,10,12};   //大月
    private static final int FEB = 2;                 //二月
    private static final int MOST_DAYS_OF_FEB = 29;
    private static final int LEAST_DAYS_OF_FEB = 28;


    public static int getMonthDays(int year, int month){
        //判断是不是闰年
        if(year % 400 == 0){
            //世纪闰年
            if(month == FEB)
                return MOST_DAYS_OF_FEB;
        }
        if(year % 100 != 0 && year % 4 == 0){
            //普通闰年
            if(month == FEB)
                return MOST_DAYS_OF_FEB;
        }
        if(month == FEB)
            return LEAST_DAYS_OF_FEB;
        else if(isContainKey(longMonth,month))
            return 31;
        else return 30;
    }

    public static int getFirstDayWeek(int year, int month){
        java.util.Calendar currentCal = java.util.Calendar.getInstance();
        currentCal.set(currentCal.DAY_OF_MONTH, 1);
//        java.util.Date date = currentCal.getTime();
//        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("E");
        return currentCal.get(Calendar.DAY_OF_WEEK);
    }

    //自定义判断是否包含在数组里
    private static boolean isContainKey(int[] keys, int targetValue)
    {
        if (keys == null || keys.length == 0)
        {
            return false;
        }

        for (int num : keys)
        {
            if(targetValue == num)
                return true;
        }

        return false;
    }

}
