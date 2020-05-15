package com.interfaceproject.utils.hxutils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author lgl
 */
public class DateUtils {

    public final static String YYYY = "yyyy";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String MM_DDHHMMSSSS = "MM-dd HH:mm:ss";
    public final static String HHMM = "HH:mm";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String MM_DD = "MM-dd";
    public final static String HH_MM_SS = "HH:mm:ss";

    /**
     * 返回YYYY-MM 为格式的时间字符
     *
     * @return String
     */
    public static String getSysmonth() {
        return DateTime.now().toString(YYYY_MM);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符
     *
     * @return String
     */
    public static String getSysdate() {
        return DateTime.now().toString(YYYY_MM_DD);
    }

    /**
     * 返回YYYY-MM-DD HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getSystime() {
        return DateTime.now().toString(YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 返回MM-ddHH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getDateAndTime() {
        return DateTime.now().toString(MM_DDHHMMSSSS);
    }

    /**
     * 返回HH:mm 为格式的时间字符
     *
     * @return String
     */
    public static String getTime() {
        return DateTime.now().toString(HHMM);
    }

    /**
     * @param format
     * @return
     */
    public static String dateToStr(String format) {
        return DateTime.now().toString(format);
    }

    /**
     * 返回YYYYMMDDHHmmss 为格式的时间字符
     *
     * @return String
     */
    public static String getSystimeNoSign() {
        return DateTime.now().toString(YYYYMMDDHHMMSS);
    }

    /**
     * 返回YYYYMMDD 为格式的时间字符
     *
     * @return String
     */
    public static String getSysdateNoSign() {
        return DateTime.now().toString(YYYYMMDD);
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     * 返回当前年
     *
     * @return int
     */
    public static int getYear() {
        return DateUtil.year(DateUtil.date());
    }

    /**
     * 返回当前月份
     *
     * @return int
     */
    public static int getMonth() {
        return DateUtil.month(DateUtil.date());
    }

    /**
     * 返回当天是当月的第几号
     *
     * @return int
     */
    public static int getDay() {
        return DateUtil.dayOfMonth(DateUtil.date());
    }

    /**
     * 返回当前小时
     *
     * @return String
     */
    public static int getHour() {
        return DateUtil.thisHour(true);
    }

    /**
     * 返回当前毫秒数
     *
     * @return String
     */
    public static long getMillisecond() {
        return DateUtil.millsecond(DateUtil.date());
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符,并且日加上一年
     *
     * @return String
     * @author zpy
     */
    public static String getSysdateAddAYear() {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.YEAR, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符,并且日加上一天
     *
     * @return String
     * @author zpy
     */
    public static String getSysdateAddADay() {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符,并且小时加上传入的个数
     *
     * @return String
     * @author zpy
     */
    public static String getSysdateAddHour(int i) {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.HOUR, i);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }


    /**
     * 获取几天前的时间 时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param beforeDay
     * @return
     */
    public static String getByBeforeDay(int beforeDay) {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -beforeDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String towDaysBefore = sdf.format(cal1.getTime());
        return towDaysBefore;
    }

    /**
     * 比较日期dt1 在dt2前返回1，dt1在dt2后返回-1，相等返回0
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 把日期字符串转换为具体日期刊
     *
     * @param dateString
     * @param format     可以是yyyy-MM-dd HH:mm:ss，yyyyMMddHHmmss，yyyy-MM-dd任何需要得到的模式
     * @return
     */
    public static Date stringToDate(String dateString, String format) {
        Date tempDate = null;
        if (dateString == null) {
            return tempDate;
        }
        if ("".equals(dateString)) {
            return tempDate;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            tempDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempDate;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static String getYearLastDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(currYearLast);
    }

    /**
     * 相差时间天数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuot(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * 根据传入参数，返回卡证有效截止日期
     *
     * @param isSpanPeriod 是否跨自然周期
     * @param finalEndDate 固定截止日期
     *                     IsSpanPeriod=1 返回当前时间加一年
     *                     IsSpanPeriod=0 用FinalEndDate和当前年末比较，谁更早，返回谁
     * @return
     */
    public static String getCardEndDate(String isSpanPeriod, String finalEndDate) {
        if (isSpanPeriod == null) {
            return "";
        }
        if ("1".equals(isSpanPeriod)) {
            return getSysdateAddAYear() + " 24:00:00";
        } else if ("0".equals(isSpanPeriod)) {
            String aa = getYearLastDay(getYear()) + " 24:00:00";
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                if (finalEndDate == null) {
                    return aa;
                }
                int num = compareDate(dateformat.parse(aa), dateformat.parse(finalEndDate));
                if (num == -1) {
                    return aa;
                } else if (num >= 0) {
                    return finalEndDate;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }


    /**
     * 根据传入参数，判断dt1是否比dt2晚 hours个小时
     *
     * @param dt1
     * @param dt2
     * @param hours 小时数
     * @return true 大于  false 小于
     */
    public static boolean compareDate(String dt1, String dt2, int hours) {
        Date tempDate1 = stringToDate(dt1, "yyyy-MM-dd HH:mm:ss");
        Date tempDate2 = stringToDate(dt2, "yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(tempDate1);
        long d1 = c.getTimeInMillis();
        c.setTime(tempDate2);
        long d2 = c.getTimeInMillis();
        return (d1 - d2) > (1000 * 60 * 60) * hours;
    }

    /**
     * 毫秒数转换为"yyyy-MM-dd HH:mm:ss"格式时间
     *
     * @return String
     */
    public static String getMillisecond(Long millisecond) {
        try {
            Date date = new Date(millisecond);
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
            return sdf.format(date);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 取得当月天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        //把日期设置为当月第一天
        a.set(Calendar.DATE, 1);
        //日期回滚一天，也就是最后一天
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 返回当前月份的yyyy-MM-dd的数组
     *
     * @return
     */
    public static String[] getCurrentMonthAllDaysArray() {
        String prefixDate = getSysmonth();
        int days = getCurrentMonthLastDay();
        String[] daysArray = new String[days];
        for (int i = 0; i < days; i++) {
            if (i < 9) {
                daysArray[i] = prefixDate + "-0" + (i + 1);
            } else {
                daysArray[i] = prefixDate + "-" + (i + 1);
            }
        }
        return daysArray;
    }

    /**
     * 返回YYYY_MM_DD_HH_mm_ss 为格式的时间字符，计算时间 加上天数后的日期
     *
     * @param dateStr
     * @param addDay
     * @return
     */
    public static String getStrDateToAddDay(String dateStr, int addDay) {
        Date date = stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, addDay);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
    }

    /**
     * 返回YYYY_MM_DD_HH_mm_ss 为格式的时间字符，计算时间 加上小时后的日期
     *
     * @param dateStr
     * @param addHour
     * @return
     */
    public static String getStrDateToAddHour(String dateStr, int addHour) {
        Date date = stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.HOUR, addHour);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
    }

    /**
     * 返回YYYY_MM_DD_HH_mm_ss 为格式的时间字符，计算时间 加上分钟后的日期
     *
     * @param dateStr
     * @param addMinutes
     * @return
     */
    public static String getStrDateToAddMinutes(String dateStr, int addMinutes) {
        Date date = stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.MINUTE, addMinutes);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
    }


    /**
     * 判断 当前时间在开始时间与结束时间之间
     *
     * @param startTime 开始时间  yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间  yyyy-MM-dd HH:mm:ss
     * @return 计算错误返回0
     */
    public static Boolean compareCurrentDate(String startTime, String endTime, String format) {
        Date startDate = DateUtils.stringToDate(startTime, format);
        Date nowDate = DateUtils.stringToDate(DateUtils.getSystime(), format);
        Date endDate = DateUtils.stringToDate(endTime, format);
        long d1 = startDate.getTime();
        long d2 = nowDate.getTime();
        long d3 = endDate.getTime();
        if ((d3 > d2) && (d2 > d1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断 当前时间在开始时间与结束时间之间 HH:mm
     *
     * @param startTime 开始时间  HH:mm
     * @param endTime   结束时间  HH:mm
     * @return 计算错误返回0
     */
    public static Boolean compareCurrentTime(String startTime, String endTime, String format) {
        Date startDate = DateUtils.stringToDate(startTime, format);
        Date nowDate = DateUtils.stringToDate(DateUtils.getTime(), format);
        Date endDate = DateUtils.stringToDate(endTime, format);
        long d1 = startDate.getTime();
        long d2 = nowDate.getTime();
        long d3 = endDate.getTime();
        if ((d3 > d2) && (d2 > d1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 相差时间秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuotS(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * 判断 时间1是否大于时间2
     *
     * @return 计算错误返回0
     */
    public static Boolean compareDateTime(String time1, String time2, String format) {
        Date date1 = DateUtils.stringToDate(time1, format);
        Date date2 = DateUtils.stringToDate(time2, format);
        if (date1.getTime() > date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回MM-DD 为格式的时间字符
     *
     * @return String
     */
    public static String getSysdate2() {
        return DateTime.now().toString(MM_DD);
    }

    /**
     * 返回YYYY-MM-DD HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getSystime2() {
        return DateTime.now().toString(HH_MM_SS);
    }

    /**
     * 计算出开始时间和结束时间的毫秒差，判断是否超过设定的超时时间，返回描述时间和描述信息字符串
     *
     * @param overrideTime 设定的超时时间
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return
     */
    public static String millisecondDifference(long overrideTime, long startTime, long endTime) {
        long s = endTime - startTime;
        String str = s > overrideTime ? s + "ms,超时" : s + "ms";
        return str;
    }

    /**
     * 求相差时间天数（不是整天数的，算多一天）
     *
     * @param time1  结束时间
     * @param time2  开始时间
     * @param format 时间格式
     * @return
     */
    public static long getDaysNumber(String time1, String time2, String format) {
        long quot = 0;
        long resNum = 0;
        SimpleDateFormat ft = new SimpleDateFormat(format);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            resNum = date1.getTime() - date2.getTime();
            quot = resNum / 86400000;
            if (resNum % 86400000 > 0) {
                quot += 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }
}
