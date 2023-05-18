package com.deng.proj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:00
 * @Version 1.0
 */
public class AppDateUtil {
    public static String getFormatTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = format.format(new Date());
        return string;
    }

    public static String getFormatTime(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String string = format.format(new Date());
        return string;
    }

    public static String getFormatTime(String pattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String string = format.format(date);
        return string;
    }

}
