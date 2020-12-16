package com.jnu.youownme.calendar_new;

import java.util.Calendar;

public class DateUtils{
    private static int[] longMonth = {1,3,5,7,8,10,12};   //大月
    private static final int FEB = 2;                 //二月
    private static final int MOST_DAYS_OF_FEB = 29;
    private static final int LEAST_DAYS_OF_FEB = 28;


//    public static int getMonthDays(int year, int month){
//        //判断是不是闰年
//        if(year % 400 == 0){
//            //世纪闰年
//            if(month == FEB)
//                return MOST_DAYS_OF_FEB;
//        }
//        if(year % 100 != 0 && year % 4 == 0){
//            //普通闰年
//            if(month == FEB)
//                return MOST_DAYS_OF_FEB;
//        }
//        if(month == FEB)
//            return LEAST_DAYS_OF_FEB;
//        else if(isContainKey(longMonth,month))
//            return 31;
//        else return 30;
//    }

    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
                    return 29;
                }else{
                    return 28;
                }
            default:
                return  -1;
        }
    }
    /**
     * 返回当前月份1号位于周几
     * @param year
     * 		年份
     * @param month
     * 		月份，传入系统获取的，不需要正常的
     * @return
     * 	日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据列明获取周
     * @param column
     * @return
     */
    public static String getWeekName(int column){
        switch(column){
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            default :
                return "";
        }
    }

//    public static int getFirstDayWeek(int year, int month){
//        java.util.Calendar currentCal = java.util.Calendar.getInstance();
//        currentCal.set(currentCal.DAY_OF_MONTH, 1);
////        java.util.Date date = currentCal.getTime();
////        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("E");
//        return currentCal.get(Calendar.DAY_OF_WEEK);
//    }
//
//    //自定义判断是否包含在数组里
//    private static boolean isContainKey(int[] keys, int targetValue)
//    {
//        if (keys == null || keys.length == 0)
//        {
//            return false;
//        }
//
//        for (int num : keys)
//        {
//            if(targetValue == num)
//                return true;
//        }
//
//        return false;
//    }

}
