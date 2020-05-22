package com.seatrend.vendor.allinspection.utils;


import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by ly on 2020/5/13 10:37
 */
public class CalenderUtils {
    //获得当前年月日时分秒星期
    public static String getTime() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String mSecond = String.valueOf(c.get(Calendar.SECOND));//秒

        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        if (c.get(Calendar.HOUR_OF_DAY) < 10) {
            mHour = "0" + mHour;
        }
        if (c.get(Calendar.MINUTE) < 10) {
            mMinute = "0" + mMinute;
        }
        if (c.get(Calendar.SECOND) < 10) {
            mSecond = "0" + mSecond;
        }

        return mYear + "年" + mMonth + "月" + mDay + "日" + "  " + mHour + ":" + mMinute + ":" + mSecond + "  " + "(周" + mWay + ")";
//        return mMinute + ":" + mSecond;
    }

    //计时器
    public static String countTime(int i) {
        if (i < 10) {
            return "00" + ":" + "0" + i;
        }
        if (i < 60) {
            return "00" + ":" + i;
        }
        if (i == 60) {
            return "01" + ":" + "00";
        }
        if (i < 600) {

            return "0" + (i / 60) + ":" + (((((i % 60) / 10) == 0)) ? "0" + (i % 60) : (i % 60));
        }
        if (i < 3600) {
            return i / 60 + ":" + i % 60;
        }
        return "59:59";
    }
}
