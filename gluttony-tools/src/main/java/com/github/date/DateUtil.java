package com.github.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 拓仲 on 2020/4/19
 */
public class DateUtil {


    public static String convertAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    public static String convertAsString(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDate);
    }

    public static LocalDateTime convertAsDateTime(String dateTime, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTime, df);
    }

    public static LocalDate convertAsLocalDate(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DateConstant.DT_STANDARD_DATE);
        return LocalDate.parse(date, df);
    }

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
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

    public static int getDayOfWeek() {
        return LocalDateTime.now().getDayOfWeek().getValue();
    }

    public static int getDayOfWeek(String date) {
        LocalDate localDate = DateUtil.convertAsLocalDate(date);
        return localDate.getDayOfWeek().getValue();
    }

    public static int getDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    public static int getDayOfMonth(String date) {
        LocalDate localDate = DateUtil.convertAsLocalDate(date);
        return localDate.getDayOfMonth();
    }

    public static String getStartDate(String startTime) {
        LocalDate start = DateUtil.convertAsLocalDate(startTime);
        LocalDateTime minDateTime = start.atTime(0, 0, 0);
        return DateUtil.convertAsString(minDateTime, DateConstant.DT_STANDARD_DATETIME);
    }

    public static String getEndDate(String endTime) {
        LocalDate end = DateUtil.convertAsLocalDate(endTime);
        LocalDateTime maxDateTime = end.atTime(23, 59, 59);
        return DateUtil.convertAsString(maxDateTime, DateConstant.DT_STANDARD_DATETIME);
    }

    public static int oddSecondsOfDay() {
        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
        return (int) seconds;
    }

    public static int oddSecondsOfWeek() {
        LocalDateTime maxDayOfWeek = LocalDateTime.now().with(TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()))).plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), maxDayOfWeek);
        return (int) seconds;
    }

    public static int oddSecondOfMonth() {
        LocalDateTime localDateTime = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), localDateTime);
        return (int) seconds;
    }

    public static int oddSecondOfYear() {
        LocalDateTime localDateTime = LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear()).plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), localDateTime);
        return (int) seconds;
    }

    public static int oddSecondOfSpecial(String date) {
        LocalDate localDate = DateUtil.convertAsLocalDate(date);
        LocalDateTime localDateTime = localDate.atTime(0, 0, 0);
        long seconds = Duration.between(LocalDateTime.now(), localDateTime).getSeconds();
        return (int) seconds;
    }

    public static boolean isBefore(String date) {
        LocalDate localDate = DateUtil.convertAsLocalDate(date);
        return LocalDate.now().isBefore(localDate);
    }

    public static boolean isAfter(String date) {
        LocalDate localDate = DateUtil.convertAsLocalDate(date);
        return LocalDate.now().isAfter(localDate);
    }

    public static LocalDate nDaysOfAfter(int d) {
        return LocalDate.now().plusDays(d);
    }

    public static LocalDate nDaysOfAfter(String date, int d) {
        LocalDate localDate = DateUtil.convertAsLocalDate(date);
        return localDate.plusDays(d);
    }


    public static void main(String[] args) {
        System.out.println(isBefore("2020-04-20"));
        System.out.println(isAfter("2020-04-20"));
        System.out.println(isAfter("2020-04-19"));
        System.out.println(convertAsString(nDaysOfAfter("2020-06-09", -1), "yyyyMMdd"));
        System.out.println(oddSecondsOfDay());
        System.out.println(oddSecondsOfWeek());
    }


}
