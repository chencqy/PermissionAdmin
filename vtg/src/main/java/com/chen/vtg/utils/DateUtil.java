package com.chen.vtg.utils;

import java.time.LocalDate;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 17:15
 * @Description :
 * @Modify by :
 */
public class DateUtil {

    public static String getToday() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        StringBuilder buf = new StringBuilder(8);
        return buf.append(year).append(month < 10 ? "0" : "").append(month).append(day < 10 ? "0" : "").append(day)
                .toString();
    }
}
