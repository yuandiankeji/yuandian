package com.yuandian.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZDateUtils {
    public static long SECOND = 1000;
    public static long MINUTE = 60 * SECOND;
    public static long FIVE_MINUTE = 5 * MINUTE;
    public static long ONE_HOUR = 60 * MINUTE;
    public static long SIX_HOUR = 6 * ONE_HOUR;
    public static long ONE_DAY = 24 * ONE_HOUR;

    public static int SECOND_SECOND = 1;
    public static int MINUTE_SECOND = (int) (MINUTE / SECOND);
    public static int SIX_HOUR_SECOND = (int) (SIX_HOUR / SECOND);
    /**
     * 默认的日期格式
     */
    public static final String DEFAULT_DATE_FAMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转日期 按指定格式
     *
     * @param stringValue
     * @param format
     */
    public static Date stringToDate(String stringValue, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(stringValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转日期 按默认格式
     *
     * @param stringValue
     */
    public static Date stringToDate(String stringValue) {
        return stringToDate(stringValue, DEFAULT_DATE_FAMAT);
    }

    /**
     * 格式化输出日期
     *
     * @param date
     * @param format
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.format(date);
    }

    /**
     * 日期转换为默认格式字符串
     *
     * @param date
     */
    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATE_FAMAT);
    }

    /**
     * 获取当前是每周的周几
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取当期周是一年中的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 两日期相差小时数(去除天数)
     */
    private static final int DELAY_TYPE_HOUR = 6;
    /**
     * 两日期相差总天数
     */
    private static final int DELAY_TYPE_TOTAL_DAY = 5;
    /**
     * 两日期相差总小时数
     */
    private static final int DELAY_TYPE_TOTAL_HOUR = 0;
    /**
     * 两日期相差总分钟数
     */
    private static final int DELAY_TYPE_TOTAL_MINUTE = 1;
    /**
     * 两日期相差总秒数
     */
    private static final int DELAY_TYPE_TOTAL_SECOND = 2;
    /**
     * 两日期相差分钟数(去除小时)
     */
    private static final int DELAY_TYPE_MINUTE = 3;
    /**
     * 两日期相差秒数(去除小时和分钟数)
     */
    private static final int DELAY_TYPE_SECOND = 4;

    public static int delay(Date date1, Date date2, int type) {
        long t1 = date1.getTime();
        long t2 = date2.getTime();
        long fenzi = 0;
        if (t1 < t2) {
            fenzi = t2 - t1;
        } else {
            fenzi = t1 - t2;
        }

        long fenmu = 1000;
        switch (type) {
            case DELAY_TYPE_TOTAL_DAY:
                fenmu *= 3600 * 24;
                break;
            case DELAY_TYPE_TOTAL_HOUR:
                fenmu *= 3600;
                break;
            case DELAY_TYPE_TOTAL_MINUTE:
                fenmu *= 60;
                break;
            case DELAY_TYPE_TOTAL_SECOND:
                //do nothing here
                break;
            case DELAY_TYPE_HOUR:
                fenzi %= 3600000 * 24;
                fenmu *= 3600;
                break;
            case DELAY_TYPE_MINUTE:
                fenzi %= 3600000;
                fenmu *= 60;
                break;
            case DELAY_TYPE_SECOND:
                fenzi %= 60000;
                break;
            default:
                return 0;
        }

        return (int) (fenzi / fenmu);
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param date1
     * @param date2
     */
    public static int delayTotalDay(Date date1, Date date2) {
        return delay(date1, date2, DELAY_TYPE_TOTAL_DAY);
    }

    /**
     * 计算两个日期相差的小时数
     *
     * @param date1
     * @param date2
     */
    public static int delayTotalHour(Date date1, Date date2) {
        return delay(date1, date2, DELAY_TYPE_TOTAL_HOUR);
    }

    /**
     * 计算两个日期相差的总分钟数
     *
     * @param date1
     * @param date2
     */
    public static int delayTotalMinute(Date date1, Date date2) {
        return delay(date1, date2, DELAY_TYPE_TOTAL_MINUTE);
    }

    /**
     * 计算两个日期相差的总秒数
     *
     * @param date1
     * @param date2
     */
    public static int delayTotalSecond(Date date1, Date date2) {
        return delay(date1, date2, DELAY_TYPE_TOTAL_SECOND);
    }

    /**
     * 计算两个日期相差的分钟数
     *
     * @param date1
     * @param date2
     */
    public static int delayMinute(Date date1, Date date2) {
        return delay(date1, date2, DELAY_TYPE_MINUTE);
    }

    /**
     * 计算两个日期相差的秒数
     *
     * @param date1
     * @param date2
     */
    public static int delaySecond(Date date1, Date date2) {
        return delay(date1, date2, DELAY_TYPE_SECOND);
    }

    /**
     * 之后
     */
    private static final int ACTION_AFTER = 0;
    /**
     * 之前
     */
    private static final int ACTION_BEFORE = 1;
    /**
     * 天
     */
    private static final int ACTION_TYPE_DAY = 0;
    /**
     * 小时
     */
    private static final int ACTION_TYPE_HOUR = 1;
    /**
     * 分钟
     */
    private static final int ACTION_TYPE_MINUTE = 2;
    /**
     * 秒
     */
    private static final int ACTION_TYPE_SECOND = 3;

    /**
     * 获取count(10) type(分钟 小时 天 秒) action(之后 之前) 的日期
     *
     * @param date
     * @param type
     * @param action
     * @param count
     */
    public static Date getDate(Date date, int type, int action, int count) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        switch (type) {
            case ACTION_TYPE_DAY:
                if (action == ACTION_AFTER) {
                    now.set(Calendar.DATE, now.get(Calendar.DATE) + count);
                } else if (action == ACTION_BEFORE) {
                    now.set(Calendar.DATE, now.get(Calendar.DATE) - count);
                }
                break;
            case ACTION_TYPE_HOUR:
                if (action == ACTION_AFTER) {
                    now.set(Calendar.HOUR, now.get(Calendar.HOUR) + count);
                } else if (action == ACTION_BEFORE) {
                    now.set(Calendar.HOUR, now.get(Calendar.HOUR) - count);
                }
                break;
            case ACTION_TYPE_MINUTE:
                if (action == ACTION_AFTER) {
                    now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + count);
                } else if (action == ACTION_BEFORE) {
                    now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - count);
                }
                break;
            case ACTION_TYPE_SECOND:
                if (action == ACTION_AFTER) {
                    now.set(Calendar.SECOND, now.get(Calendar.SECOND) + count);
                } else if (action == ACTION_BEFORE) {
                    now.set(Calendar.SECOND, now.get(Calendar.SECOND) - count);
                }
                break;
        }

        return now.getTime();
    }

    /**
     * 得到某天前几天日期
     *
     * @param date
     * @param day
     */
    public static Date getDateBefore(Date date, int day) {
        return getDate(date, ACTION_TYPE_DAY, ACTION_BEFORE, day);
    }

    /**
     * 得到某天后几天日期
     *
     * @param date
     * @param day
     */
    public static Date getDateAfter(Date date, int day) {
        return getDate(date, ACTION_TYPE_DAY, ACTION_AFTER, day);
    }

    /**
     * 得到某天多少小时前的日期
     *
     * @param date
     * @param hour
     */
    public static Date getHourBefore(Date date, int hour) {
        return getDate(date, ACTION_TYPE_HOUR, ACTION_BEFORE, hour);
    }

    /**
     * 得到某天多少小时后的日期
     *
     * @param date
     * @param hour
     */
    public static Date getHourAfter(Date date, int hour) {
        return getDate(date, ACTION_TYPE_HOUR, ACTION_AFTER, hour);
    }

    /**
     * 得到某天多少分钟后的日期
     *
     * @param date
     * @param minute
     */
    public static Date getMinuteAfter(Date date, int minute) {
        return getDate(date, ACTION_TYPE_MINUTE, ACTION_AFTER, minute);
    }

    /**
     * 得到某天多少分钟前的日期
     *
     * @param date
     * @param minute
     */
    public static Date getMinuteBefore(Date date, int minute) {
        return getDate(date, ACTION_TYPE_MINUTE, ACTION_BEFORE, minute);
    }

    /**
     * 得到某天多少秒钟前的日期
     *
     * @param date
     * @param second
     */
    public static Date getSecondBefore(Date date, int second) {
        return getDate(date, ACTION_TYPE_SECOND, ACTION_BEFORE, second);
    }

    /**
     * 得到某天多少秒钟前的日期
     *
     * @param date
     * @param second
     */
    public static Date getSecondAftere(Date date, int second) {
        return getDate(date, ACTION_TYPE_SECOND, ACTION_AFTER, second);
    }

    /**
     * 两日起是否是同一天
     *
     * @param date1
     * @param date2
     */
    public static boolean areSameDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (dateToString(date1, "yyyy-MM-dd").equals(dateToString(date2, "yyyy-MM-dd"))) {
            return true;
        }
        return false;
    }

    /**
     * 是否介于两个日期之间
     *
     * @param date
     * @param date1
     * @param date2
     */
    public static boolean isBetweenDays(Date date, Date date1, Date date2) {
        if (date == null || date1 == null || date2 == null) {
            return false;
        }
        if (date1.before(date2)
                && date.after(date1)
                && date.before(date2)) {
            return true;
        } else if (date.before(date1)
                && date.after(date2)) {
            return true;
        }
        return false;
    }



    public static long getSeconds() {
        long now = System.currentTimeMillis();
        return now / SECOND;
    }

    public static long getNow() {
        return System.currentTimeMillis();
    }

    public static String getDateString(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(c.getTime());

    }
}
