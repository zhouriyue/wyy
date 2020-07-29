package com.gxuwz.beethoven.util;

public class DateUtil {

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
