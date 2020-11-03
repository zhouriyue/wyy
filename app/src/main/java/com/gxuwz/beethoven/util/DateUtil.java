package com.gxuwz.beethoven.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取该时间的星座
     * @param date
     * @return
     */
    public static String getAstro(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");
        String monthDay = df.format(date);
        String md[] = monthDay.split("-");
        int month = Integer.parseInt(md[0]);
        int day = Integer.parseInt(md[1]);
        String[] starArr = {"魔羯座","水瓶座", "双鱼座", "牡羊座",
                "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座" };
        int[] DayArr = {22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22};  // 两个星座分割日
        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < DayArr[month - 1]) {
            index = index - 1;
        }
        // 返回索引指向的星座string
        return starArr[index];
    }

    /**
     * 格式年  format year
     * @param date
     * @return
     */
    public static String formateYear(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return df.format(date);
    }

    /**
     * 格式化为  xxxx年xx月
     * @param date
     * @return
     */
    public static String formatYearMonthZh(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
        return df.format(date);
    }

    /**
     * 获取连个date之间的天数
     * @param start
     * @param end
     * @return
     */
    public static long computeDay(Date start,Date end) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(start);
        long time1=cal.getTimeInMillis();
        cal.setTime(end);
        long time2=cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        long count = Long.parseLong(String.valueOf(between_days));
        return count;
    }

    /**
     * 获取连个date之间的年数
     * @param start
     * @param end
     * @return
     */
    public static int computeYear(Date start,Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);
        int count = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
        return count;
    }

    public static Date parseString(String dateStr) {
        if(dateStr==null) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return df.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static String simpleFormat(Date date) {
        if(date!=null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        }
        return null;
    }

    /**
     * 通过秒数得到00:00格式
     * @param s
     * @return
     */
    public static String sToDate(int s) {
        String result = "";
        int min = s/60;
        int S = s % 60;
        if(min<10) {
            result += "0"+min;
        } else {
            result += min;
        }
        result+=":";
        if(S<10) {
            result += "0"+S;
        } else {
            result += S;
        }
        return result;
    }
}
