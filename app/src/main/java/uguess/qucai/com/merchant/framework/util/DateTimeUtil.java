/*
 * Copyright(c) 2015, QuCai, Inc. All rights reserved.
 * This software is the confidential and proprietary information of QuCai, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with QuCai.
 */
package uguess.qucai.com.merchant.framework.util;

import android.text.TextUtils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DateTimeUtil.java是启程系统的日期工具类。
 *
 * @author 花树峰
 * @version 1.0 2015-3-3
 */
public class DateTimeUtil {

    public static final String time_separator = ":";

    public static final String date_separator = "-";

    public static final int day_millis = 86400000;
    public static final String day_text = "天前";


    public static final int hour_millis = 3600000;
    public static final String hour_text = "小时前";

    public static final int minutes_millis = 60000;
    public static final String minutes_text = "分钟前";

    public static final String now_text = "刚刚";


    /**
     * yyyy-MM-dd HH:mm:ss格式化对象。
     */
    private static final DateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyyMMddHHmmss格式化对象。
     */
    private static final DateFormat yyyyMMddHHmmssDf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * yyyyMMdd格式化对象。
     */
    private static final DateFormat yyyyMMddDf = new SimpleDateFormat("yyyyMMdd");

    /**
     * yyyy-MM-dd格式化对象。
     */
    private static final DateFormat yyyyMMdd10Df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * HH:mm:ss格式化对象。
     */
    private static final DateFormat hhmmss8Df = new SimpleDateFormat("HH:mm:ss");

    /**
     * yyyy/MM/dd/HH/mm/ss格式化对象。
     */
    private static final DateFormat yyyyMMddHHmmDf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");

    /**
     * yyyy-MM-dd HH:mm格式化对象。
     */
    private static final DateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * MM-dd HH:mm格式化对象。
     */
    private static final DateFormat MMddHHmmFormat = new SimpleDateFormat("MM-dd HH:mm");

    /**
     * HH:mm格式化对象。
     */
    private static final DateFormat HHmmFormat = new SimpleDateFormat("HH:mm");

    /**
     * yyyyMMddHHmm格式化对象。
     */
    private static final DateFormat longTime12Format = new SimpleDateFormat("yyyyMMddHHmm");

    /**
     * yyyy年MM月dd日格式化对象。
     */
    private static final DateFormat yyyyMMddChineseDf = new SimpleDateFormat("yyyy年MM月dd日");

    /**
     * 获取间隔时间字符串。
     *
     * @param date 日期对象
     * @return 间隔时间字符串
     */
    public static String getTimeInterval(Date date) {
        Date current = new Date(System.currentTimeMillis());
        long diff = current.getTime() - date.getTime();//这样得到的差值是微秒级别
        long days = diff / (day_millis);
        long hours = (diff - days * (day_millis)) / (hour_millis);
        long minutes = (diff - days * (day_millis) - hours * (hour_millis)) / (minutes_millis);
        if (days > 0) {
            return days + day_text;
        } else if (hours > 0) {
            return hours + hour_text;
        } else if (minutes > 0) {
            return minutes + minutes_text;
        } else {
            return now_text;
        }
    }

    /**
     * 计算年龄
     *
     * @param birthday 生日字符串（yyyy-MM-dd）
     * @return 年龄
     */
    public static String getAge(String birthday) {
        return getAge(parseByyyyyMMdd10(birthday));
    }

    /**
     * 计算年龄
     *
     * @param birthday 生日日期对象
     * @return 年龄
     */
    public static String getAge(Date birthday) {
        Calendar cal = Calendar.getInstance();
        /*判断生日是否有效*/
        if (cal.before(birthday)) {
            return null;
        }
        /*获取现在的年月日*/
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        /*获取生日的年月日*/
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow < monthBirth
                age--;
            }
        }
        return String.valueOf(age);
    }

    /**
     * 以参数format日期格式，格式化日期对象。
     *
     * @param date   日期对象
     * @param format 日期格式
     * @return 格式化之后的日期字符串
     */
    public synchronized static String format(Date date, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 以yyyyMMddHHmmss格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyyMMddHHmmss格式的日期字符串
     */
    public synchronized static String format(Date date) {
        return yyyyMMddHHmmssDf.format(date);
    }

    /**
     * 以yyyy-MM-dd HH:mm:ss格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyy-MM-dd HH:mm:ss格式的日期字符串
     */
    public synchronized static String formatLongDate(Date date) {
        return longDateFormat.format(date);
    }

    /**
     * 以yyyyMMdd格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyyMMdd格式的日期字符串
     */
    public synchronized static String formatByyyyyMMdd(Date date) {
        return yyyyMMddDf.format(date);
    }

    /**
     * 以yyyy-MM-dd格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyy-MM-dd格式的日期字符串
     */
    public synchronized static String formatByyyyyMMdd10(Date date) {
        return yyyyMMdd10Df.format(date);
    }

    /**
     * 以HH:mm:ss格式，格式化时间对象。
     *
     * @param time 时间对象
     * @return HH:mm:ss格式的时间字符串
     */
    public synchronized static String formatByHHmmss8(Time time) {
        return hhmmss8Df.format(time);
    }

    /**
     * 以yyyy/MM/dd/HH/mm/ss格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyy/MM/dd/HH/mm/ss格式的日期字符串
     */
    public synchronized static String formatByyyyyMMdd16(Date date) {
        return yyyyMMddHHmmDf.format(date);
    }

    /**
     * 以yyyy-MM-dd HH:mm格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyy-MM-dd HH:mm格式的日期字符串
     */
    public synchronized static String formatLongTime(Date date) {
        return longTimeFormat.format(date);
    }

    /**
     * 以yyyyMMddHHmm格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyyMMddHHmm格式的日期字符串
     */
    public synchronized static String formatLongTime12(Date date) {
        return longTime12Format.format(date);
    }

    /**
     * 以yyyy年MM月dd日格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return yyyy年MM月dd日格式的日期字符串
     */
    public synchronized static String formatByyyyyMMddChinese(Date date) {
        return yyyyMMddChineseDf.format(date);
    }

    /**
     * 以参数format日期格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @param format  日期格式
     * @return 日期对象
     */
    public synchronized static Date parse(String strDate, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以yyyy-MM-dd HH:mm:ss格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @return 日期对象
     */
    public synchronized static Date parseLongDate(String strDate) {
        try {
            return longDateFormat.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以yyyyMMddhhmmss格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @return 日期对象
     */
    public synchronized static Date parse(String strDate) {
        try {
            return yyyyMMddHHmmssDf.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以yyyyMMdd格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @return 日期对象
     */
    public synchronized static Date parseByyyyyMMdd(String strDate) {
        try {
            return yyyyMMddDf.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以yyyy-MM-dd格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @return 日期对象
     */
    public synchronized static Date parseByyyyyMMdd10(String strDate) {
        try {
            return yyyyMMdd10Df.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以HH:mm:ss格式，把字符串时间转化为时间对象。
     *
     * @param strTime 字符串时间
     * @return 时间对象
     */
    public synchronized static Date parseByHHmmss8(String strTime) {
        try {
            return hhmmss8Df.parse(strTime);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以yyyy-MM-dd HH:mm格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @return 日期对象
     */
    public synchronized static Date parseLongTime(String strDate) {
        try {
            return longTimeFormat.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    /**
     * 以yyyyMMddHHmm格式，把字符串日期转化为日期对象。
     *
     * @param strDate 字符串日期
     * @return 日期对象
     */
    public synchronized static Date parseLongTime12(String strDate) {
        try {
            return longTime12Format.parse(strDate);
        } catch (ParseException e) {
            // 忽略;
        }
        return null;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }

    /**
     * 以MM-dd HH:mm格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return MM-dd HH:mm格式的日期字符串
     */
    public synchronized static String formatMMddHHmm(Date date) {
        return MMddHHmmFormat.format(date);
    }

    /**
     * 以HH:mm格式，格式化日期对象。
     *
     * @param date 日期对象
     * @return MM-dd HH:mm格式的日期字符串
     */
    public synchronized static String formatHHmm(Date date) {
        return HHmmFormat.format(date);
    }

    /**
     * 确定日期对应的星座
     *
     * @param birthday
     * @return 返回对应星座的字符串 如：射手座(9.12～12.23)
     */
    public static String judgeConstellationp(Calendar birthday) {
        int mMonth = birthday.get(Calendar.MONTH);
        int mDay = birthday.get(Calendar.DAY_OF_MONTH);
        String result = "";
        switch (mMonth) {
            case 0:
                if (mDay <= 19) {
                    result = "魔羯座(12.22～1.19)";
                } else {
                    result = "水瓶座(1.20～2.18)";
                }
                break;
            case 1:
                if (mDay <= 18) {
                    result = "水瓶座(1.20～2.18)";
                } else {
                    result = "双鱼座(2.19～3.20)";
                }
                break;
            case 2:
                if (mDay <= 20) {
                    result = "双鱼座(2.19～3.20)";
                } else {
                    result = "白羊座(3.21～4.19)";
                }
                break;
            case 3:
                if (mDay <= 19) {
                    result = "白羊座(3.21～4.19)";
                } else {
                    result = "金牛座(4.20～5.20)";
                }
                break;
            case 4:
                if (mDay <= 20) {
                    result = "金牛座(4.20～5.20)";
                } else {
                    result = "双子座(5.21～6.21)";
                }
                break;
            case 5:
                if (mDay <= 21) {
                    result = "双子座(5.21～6.21)";
                } else {
                    result = "巨蟹座(6.22～7.22)";
                }
                break;
            case 6:
                if (mDay <= 22) {
                    result = "巨蟹座(6.22～7.22)";
                } else {
                    result = "狮子座(7.23～8.22)";
                }
                break;
            case 7:
                if (mDay <= 22) {
                    result = "巨蟹座(6.22～7.22)";
                } else {
                    result = "处女座(8.23～9.22)";
                }
                break;
            case 8:
                if (mDay <= 22) {
                    result = "处女座(8.23～9.22)";
                } else {
                    result = "天秤座(9.23-10.23)";
                }
                break;
            case 9:
                if (mDay <= 23) {
                    result = "天秤座(9.23-10.23)";
                } else {
                    result = "天蝎座(10.24-11.22)";
                }
                break;
            case 10:
                if (mDay <= 22) {
                    result = "天蝎座(10.24～11.22)";
                } else {
                    result = "射手座(11.23～12.21)";
                }
                break;
            case 11:
                if (mDay <= 21) {
                    result = "射手座(11.23～12.21)";
                } else {
                    result = "魔羯座(12.22～1.19)";
                }
                break;
        }
        return result;
    }

    /**
     * 获得截止时间毫秒数
     *
     * @param deadlineDate
     * @return
     */
    public static long getInternalTime(Long deadlineDate) {
        if (null == deadlineDate) {
            return 0;
        }
        Calendar deadlineCalendar = Calendar.getInstance(Locale.CHINA);
        deadlineCalendar.setTimeInMillis(deadlineDate);

        Calendar systemCalendar = Calendar.getInstance(Locale.CHINA);
        long currenttime = systemCalendar.getTimeInMillis();
        long deadlinetime = deadlineCalendar.getTimeInMillis();

        long diff = deadlinetime - currenttime;//这样得到的差值是微秒级别
        return diff;
    }

    public static String longTimeToStringTime(long diff) {
        long hours = diff / (3600);
        long minutes = (diff - hours * 3600) / 60;
        long second = (diff - hours * 3600 - minutes * 60) / 60;
        return hours + ":" + minutes + ":" + second;
    }

    /**
     * 判定字符串是否为日期格式
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(date);

        boolean dateType = mat.matches();

        return dateType;
    }

    /**
     * 将用户生转换为年龄
     *
     * @param birthday
     * @return
     */
    public static int changeBirthdayToAge(String birthday) {
        if (birthday != null && !TextUtils.isEmpty(birthday)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateTimeUtil.parseByyyyyMMdd10(birthday));
            int yearOfBirthday = calendar.get(Calendar.YEAR);
            calendar.setTime(new Date());
            int currentYear = calendar.get(Calendar.YEAR);
            int age = currentYear - yearOfBirthday + 1;
            return age > 0 ? age : 0;
        } else {
            return 0;
        }
    }
}
