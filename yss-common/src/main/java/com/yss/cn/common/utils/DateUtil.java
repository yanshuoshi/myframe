package com.yss.cn.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:Shuoshi.Yan
 * @description: 时间util
 * @date: 2020/4/16 15:18
 */
public class DateUtil {
    private static final String DateFormat_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String DateFormat_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm:ss";
    private static final String DateFormat_YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";

    /**
     * @author:Shuoshi.Yan
     * @description: 字符串转Date 格式：yyyy-MM-dd HH:mm:ss
     * @date: 2020/4/16 15:18
     */
    public static Date stringToDate(String date) {
        try {
            return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_HH_MM + "]");
        }
    }

    /**
     * @author:Shuoshi.Yan
     * @description: 字符串转Date 自定义返回Date格式
     * @date: 2020/4/16 15:20
     */
    public static Date stringToDate(String formatString, String date) {
        try {
            return new SimpleDateFormat(formatString).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_HH_MM + "]");
        }
    }

    /**
     * @author:Shuoshi.Yan
     * @description: Date转String 格式：yyyyMMddHHmmss
     * @date: 2020/4/16 15:32
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM_SS).format(date);
    }

    /**
     * @author:Shuoshi.Yan
     * @description: Date转String 自定义返回格式
     * @date: 2020/4/16 15:34
     */
    public static String dateToString(String dateStr,Date date) {
        try {
            return new SimpleDateFormat(dateStr).format(date);
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_HH_MM + "]");
        }
    }

    /**
     * @author:Shuoshi.Yan
     * @description:获取当前时间的String类型
     * @date: 2020/4/16 15:23
     */
    public static String getCurrentTimeWithMinute() {
        return new SimpleDateFormat(DateFormat_YYYY_MM_DD_HH_MM).format(new Date());
    }

    /**
     * @author:Shuoshi.Yan
     * @description:获取当前时间的String类型 自定义返回格式
     * @date: 2020/4/16 15:28
     */
    public static String getCurrentTimeWithMinute(String date) {
        try {
            return new SimpleDateFormat(date).format(new Date());
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误[" + DateFormat_YYYY_MM_DD_HH_MM + "]");
        }
    }

    /**
     * @author:Shuoshi.Yan
     * @description: 获取当前月的第一天日期
     * @date: 2020/4/16 15:43
     */
    public static String dateToMonth() {
        SimpleDateFormat format = new SimpleDateFormat(DateFormat_YYYY_MM_DD);
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String fstlatDay = format.format(cal_1.getTime());
        return fstlatDay;
    }

    /**
     * @author:Shuoshi.Yan
     * @description: 当前第几季度
     * @date: 2020/4/16 15:48
     */
    public static Integer getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }
}
