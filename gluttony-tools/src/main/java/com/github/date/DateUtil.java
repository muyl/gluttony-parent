package com.github.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 拓仲 on 2020/4/19
 */
public class DateUtil {


    public static String convertAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    public static LocalDateTime convertAsDateTime(String dateTime, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTime, df);
    }

    /**
     * 获取当前时间
     *
     * @return 格式 ： yyyy-MM-dd HH:mm:ss
     */
    public static String now() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_DATETIME);
    }


    /**
     * 获取当前时间
     *
     * @return 格式 ： yyyyMMddHHmmss
     */
    public static String shortNow() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_SHORT_DATETIME);
    }


    /**
     * Little now string
     *
     * @return the string
     */
    public static String littleNow() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_LITTLE_DATETIME);
    }

    /**
     * 获取当前时间
     *
     * @return 格式 ： yyyy-MM-dd
     */
    public static String today() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_DATE);
    }

    /**
     * 获取当前时间
     *
     * @return 格式 ： yyyyMMdd
     */
    public static String shortToday() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_SHORT_DATE);
    }


    /**
     * 获取当前时间
     *
     * @return 格式 ： yyMMdd
     */
    public static String littleToday() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_LITTLE_DATE);
    }

    /**
     * Time string
     *
     * @return the HH:mm:ss
     */
    public static String time() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_TIME);
    }

    /**
     * Short time string
     *
     * @return the HHmmss
     */
    public static String shortTime() {
        return DateUtil.convertAsString(LocalDateTime.now(), DateConstant.DT_STANDARD_SHORT_TIME);
    }

    /**
     * Spend ms long
     *
     * @param preTime pre time
     * @return the long
     */
    public static long spendMs(long preTime) {
        return System.currentTimeMillis() - preTime;
    }


    public static String plusValidDays(int v) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(v).minusDays(1);
        return DateUtil.convertAsString(localDateTime, DateConstant.DT_STANDARD_DATE);
    }

    public static String plusValidDays(String d, int v) {
        LocalDateTime localDateTime = convertAsDateTime(d, DateConstant.DT_STANDARD_DATE);
        return DateUtil.convertAsString(localDateTime.plusDays(v), DateConstant.DT_STANDARD_DATE);
    }

    public static String plusValidWeeks(int v) {
        LocalDateTime localDateTime = LocalDateTime.now().plusWeeks(v).minusDays(1);
        return DateUtil.convertAsString(localDateTime, DateConstant.DT_STANDARD_DATE);
    }

    public static String plusValidMonths(int v) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(v).minusDays(1);
        return DateUtil.convertAsString(localDateTime, DateConstant.DT_STANDARD_DATE);
    }

    public static String plusValidYears(int v) {
        LocalDateTime localDateTime = LocalDateTime.now().plusYears(v).minusDays(1);
        return DateUtil.convertAsString(localDateTime, DateConstant.DT_STANDARD_DATE);
    }


    public static void main(String[] args) {
        System.out.println(plusValidDays(1));
    }


}
