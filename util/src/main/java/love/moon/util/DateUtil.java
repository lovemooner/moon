package love.moon.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: lovemooner
 * Date: 17-4-18
 * Time: 上午11:10
 */
public class DateUtil {


    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
    //一年
    public static final long YEAR_MILLION_SECONDS = 365 * 24 * 60 * 60 * 1000l;
    //一个月
    public static final long MONTH_MILLION_SECONDS = 30 * 24 * 60 * 60 * 1000l;
    //24小时
    public static final long DAY_MILLION_SECONDS = 24 * 60 * 60 * 1000;
    //一小时
    public static final long HOUR_MILLION_SECONDS = 60 * 60 * 1000;
    //一分钟
    public static final long MINUTE_MILLION_SECONDS = 60 * 1000;
    public static final long MILLOIN_SECONDS = 1000;

    public static final String DATE_STRING_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_STRING_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_DEFAULT_TO_AFTERNOON = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_CN = "yyyy年MM月dd日";
    public static final String YEAR_MONTH_DATE = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DATE_2 = "yyyy/MM/dd";
    public static final String MONTH_DATE = "MM-dd";
    public static final String DATE_FORMAT_TIME = "HH:mm";

    public static final String DATE_STRING_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_DAY2 = "yyyy.MM.dd";
    public static final String DATE_STRING_FORMAT_DAY3 = "yyyyMMdd";
    public static final String DATE_STRING_FORMAT_CN2 = "yyyy年MM月";
    public static final String DATE_STRING_FORMAT_CN3 = "yyyy年";
    public static final String DATE_STRING_FORMAT_CN4 = "MM月dd日";

    public static final String TIME = "HH:mm:ss";
    public static final String DEFAULT = "yyyy-MM-dd";
    public static final String STANDARD = "yyyy-MM-dd HH:mm";
    public static final String ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String STANDARD_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String STANDARD_CN_2 = "yyyy年MM月dd日 HH:mm";
    private static final String SIMPLE_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String DATE_STRING_FORMAT_MON_DAY_HOUR_MIN_CN = "MM月dd日 HH时mm分";
    public static final String DATE_STRING_FORMAT_YEAR_MON = "yyyy-MM";
    public static final String DATE_STRING_FORMAT_YEA_MON2 = "yyyy.MM";


    public static Date parse(String dateStr) throws ParseException {
        dateStr = dateStr.replace("Z", " UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date dt = sdf.parse(dateStr);
        System.out.println(dt.toString());
        return dt;
    }

    /**
     * 将数字类型时间转换为时间字符串
     *
     * @param dateNumber
     * @param formatStr
     * @return
     */
    public static String dateLongToStr(Long dateNumber, String formatStr) {
        if (dateNumber == null || dateNumber.equals(0L) || formatStr == null || formatStr.length() == 0) {
            return "";
        }
        Date date = new Date(dateNumber);
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    public static String dateLongToStr(Long dateNumber) {
        Date date = new Date(dateNumber);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DEFAULT);
        return sdf.format(date);
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DEFAULT);
        return sdf.format(date);
    }


    /**
     * 转化时间从长整形为指定格式日期
     *
     * @param format
     * @param time
     * @return
     */
    public static String convertDateLongToDateString(String format, Long time) {
        if (time == null || time == 0) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        Timestamp now = new Timestamp(time);
        return df.format(now);
    }

    /**
     * 转化时间从指定格式日期为长整形
     *
     * @param format
     * @param time
     * @return
     */
    public static Long convertDateStringToDateLong(String format, String time) throws ParseException {
        if (time == null || time.trim().equals("")) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date d = fmt.parse(time);
        return d.getTime();
    }

    public static String convertDateLong2SimpleCNFormat(Long time) {
        if (time == null)
            return null;
        return new SimpleDateFormat(SIMPLE_CN).format(new Date(time));
    }

    public static String fullFormat(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    /**
     * @param formatString
     * @param date
     * @return
     */
    public static String format(String formatString, Date date) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat(formatString);
        return format.format(date);
    }

    public static String getLinuxTime() {
        DateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String r = format.format(new Date());
        return r;
    }

    // 段时间
    public static String toPeriodTime(long m) {
        StringBuffer sb = new StringBuffer();
        long years = m / YEAR_MILLION_SECONDS;
        m = m - years * YEAR_MILLION_SECONDS;
        long months = m / MONTH_MILLION_SECONDS;
        m = m - months * MONTH_MILLION_SECONDS;
        long days = m / DAY_MILLION_SECONDS;
        m = m - days * DAY_MILLION_SECONDS;
        long hours = m / HOUR_MILLION_SECONDS;
        m = m - hours * HOUR_MILLION_SECONDS;
        long minutes = m / MINUTE_MILLION_SECONDS;
        if (0 != years) {
            sb.append(String.valueOf(years));
            sb.append("年");
        }
        if (0 != months) {
            sb.append(String.valueOf(months));
            sb.append("个月");
        }
        if (0 != days) {
            sb.append(String.valueOf(days));
            sb.append("天");
        }
        if (0 != hours) {
            sb.append(String.valueOf(hours));
            sb.append("小时");
        }
        if (0 != minutes) {
            sb.append(String.valueOf(minutes));
            sb.append("分钟");
        }
        return sb.toString();
    }

    public static String getISO8601Time() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
//        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }

    public static String getChinaTime(){
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(tz);
        return df.format(new Date());
    }


    //日期转换
    public static String convertDateLongToString(Long time) {
        if (time == null) {
            return "";
        }
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(d);
    }

    public static String convertDateLongToString(Long time, String format) {
        if (time == null) {
            return "";
        }
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 得到今天零点零分零秒的Long值
     */
    public static Long getTheDayTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DAY);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_DAY, sdf.format(new Date()));
    }

    /**
     * 得到当前时间，精确到分
     */
    public static Long getTheDayTime2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DAY_HOUR_MIN);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_DAY_HOUR_MIN, sdf.format(new Date()));
    }

    /**
     * 得到未来N天零点零分零秒的Long值
     */
    public static Long getInnerDayTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到未来N年
     */
    public static Long getInnerYearTime(long standardTime, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(standardTime);
//    calendar.set(Calendar.HOUR_OF_DAY, 0);
//    calendar.set(Calendar.MINUTE, 0);
//    calendar.set(Calendar.SECOND, 0);
//    calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + n);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到startTime未来N天零点零分零秒的Long值
     */
    public static Long getInnerDayTime(long standardTime, int differenceDayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(standardTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + differenceDayNum);
        return calendar.getTimeInMillis();
    }

    public static Long getInnerDayTime(String standardTime, int differenceDayNum) throws ParseException {
        if (StringUtils.isBlank(standardTime)) return null;
        Long dateLong = convertDateStringToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, standardTime);
        return getInnerDayTime(dateLong, differenceDayNum);
    }

    /**
     * 获得当前月第一天 (yyyy-MM-dd)
     */
    public static String getFirtDayOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = Calendar.getInstance();
        startCal.set(startCal.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return df.format(startCal.getTime());
    }

    /**
     * 获得当前月第一天 (yyyy-MM-dd HH:mm)
     */
    public static String getFirtDayTimeOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar startCal = Calendar.getInstance();
        startCal.set(startCal.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        String day_start = df.format(startCal.getTime());
        return day_start;
    }

    /**
     * 获取一个月之前的时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     * @throws ParseException
     */
    public static Long getOneMonthBeforeDateTime() throws ParseException {
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.MONTH, -1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date date = (Date) startCal.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }


    /**
     * 获取当前月第一天 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     * @throws ParseException
     */
    public static Long getFirstDayDateTimeOfMonth() throws ParseException {
        Calendar startCal = Calendar.getInstance();
        startCal.set(startCal.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date date = (Date) startCal.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));

    }

    /**
     * 获得当前月最后一天 (yyyy-MM-dd HH:mm)
     */
    public static String getLastDayOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 59);
        return df.format(endCal.getTime());
    }


    /**
     * 获得当前月上个月最后一天
     */
    public static Long getLastDayOfLastMonth() throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
//    cal.set(Calendar.MONTH, month - 1);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        return convertDateStringToDateLong("yyyy-MM-dd", (year + "-" + month + "-" + day));
    }

    /**
     * 获得当前月上个月第一天
     */
    public static Long getFirstDayOfLastMonth() throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        int day = cal.getActualMinimum(Calendar.DAY_OF_MONTH); // 开始天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        return convertDateStringToDateLong("yyyy-MM-dd", (year + "-" + month + "-" + day));
    }

    /**
     * 获取当前月最后一天23时59分59秒
     *
     * @return
     * @throws ParseException
     */
    public static Long getLastDayDateTimeOfMonth() throws ParseException {
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 59);
        Date date = (Date) endCal.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }

    //获取dateTimeStr 所在月的最后一天 最后时间点
    public static Long getLastDayDateTimeOfMonth(String format, String dateTimeStr) throws ParseException {
        Long dateTime = convertDateStringToDateLong(format, dateTimeStr);
        Calendar endCal = Calendar.getInstance();
        endCal.setTimeInMillis(dateTime);
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        return endCal.getTime().getTime();
    }


    /**
     * 获取指定月最后一天23时59分59秒
     *
     * @return
     * @throws ParseException
     */
    public static Long getLastDayOfMonth(Calendar cal) throws ParseException {
        cal.add(cal.MONTH, 1);
        cal.set(cal.DATE, 1);
        cal.add(cal.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTimeInMillis();

    }

    /**
     * 获取一年的最后一天 23时59分59秒
     *
     * @return
     * @throws ParseException
     */
    public static String getEndOfYear() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当年的开始 00时00分00秒
     *
     * @return
     * @throws ParseException
     */
    public static String getStartOfYear() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当年的开始 00时00分00秒
     *
     * @return
     * @throws ParseException
     */
    public static Long getStartOfYearOfLong() throws ParseException {
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", getStartOfYear());
    }

    public static Long convertDateDateShortToDateLong(String format, Date date) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        String dateStr = df.format(date);
        return convertDateStringToDateLong(format, dateStr);
    }

    public static Long getToday(String format, Date date) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        return convertDateStringToDateLong(format, df.format(date));
    }

    public static String getNowTimeStr(String format) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        return format(format, new Date());
    }

    public static Date getDateFromString(String format, String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 获取特定日期开始时间：00：00：00
     *
     * @return 特定日0时0分0秒
     * @throws ParseException
     */
    public static Long getStartTimeOfDate(String dateStr) throws ParseException {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date startDate = (Date) cal.getTime().clone();
        DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(startDate));
    }

    /**
     * 获取特定日期23：59：59
     *
     * @return 特定日期23：59：59
     * @throws ParseException
     */
    public static Long getEndTimeOfDate(String dateStr) throws ParseException {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
//    Date endDate = (Date) cal.getTime().clone();
//    DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
//    return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(endDate));
    }

    public static Long getNextMonthTime(long currentTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(currentTime));
        cal.add(Calendar.MONTH, 1);
        return cal.getTime().getTime();
    }

    /**
     * n个月的开始时间
     *
     * @param n
     * @return
     * @throws ParseException
     */
    public static Long getStartTimeOfMonth(int n) throws ParseException {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.MONTH, n);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate.getTimeInMillis();
    }

    /**
     * n个月的开始时间
     *
     * @param n
     * @return
     * @throws ParseException
     */
    public static Long getStartMonthOfTime(int n) throws ParseException {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.MONTH, n);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate.getTimeInMillis();
    }


    /**
     * 获取当前天开始时间：00：00：00
     *
     * @return 当前天0时0分0秒
     * @throws ParseException
     */
    public static Long getStartTimeOfToday() throws ParseException {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate.getTime().getTime();
    }

    /**
     * 获取当前天23：59：59
     * 1.1 modify by qxy 当前天23：59：59.999  modifyTime 2013-11-13 15:59
     *
     * @return 当前天23：59：59.999
     * @throws ParseException
     */
    public static Long getEndTimeOfToday() throws ParseException {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        return currentDate.getTimeInMillis();
//    Date date = (Date) currentDate.getTime().clone();
//    DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
//    return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(date));
    }

    /**
     * 判断目标时间是否在今天
     *
     * @param targetDate
     * @return
     */
    public static Boolean isInToday(Long targetDate) {
        if (targetDate == null) return false;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long startOfToday = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Long endOfToday = calendar.getTimeInMillis();
        if (targetDate >= startOfToday && targetDate <= endOfToday) {
            return true;
        }
        return false;
    }

    public static Boolean isInYesterday(Long targetDate) {
        if (targetDate == null) return false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long startOfYesterday = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Long endOfYesterday = calendar.getTimeInMillis();
        if (targetDate >= startOfYesterday && targetDate <= endOfYesterday) {
            return true;
        }
        return false;
    }

    /**
     * 获取昨天：00：00：00
     *
     * @return 昨天：00：00：00
     * @throws ParseException
     */
    public static Long getStartTimeOfYesterday() throws ParseException {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date date = (Date) currentDate.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }

    /**
     * 1.0  获取昨天：23：59：59
     * 1.1 modify by qxy 取昨天23：59：59.999  modifyTime 2013-11-13 15:59 未测试直接代码
     *
     * @return 昨天：23：59：59.999
     * @throws ParseException
     * @author zhangchuanlong
     * //@return   昨天：23：59：59
     */
    public static Long getEndTimeOfYesterday() throws ParseException {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        return currentDate.getTimeInMillis();
//    Date date = (Date) currentDate.getTime().clone();
//    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }

    /**
     * 两个日期间的年月日差
     *
     * @param startDate
     * @param endDate
     * @return XX年XX月XX日
     */
    public static String dateDifference(Long startDate, Long endDate, boolean hideDayIfMonth) {
        if (endDate.compareTo(startDate) < 0) {   //保证endDate>startDate
            Long tmp = startDate;
            startDate = endDate;
            endDate = tmp;
        }
        Calendar calS = Calendar.getInstance();
        calS.setTimeInMillis(startDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTimeInMillis(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        //处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE) + 1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();

        int lday = endD - startD;
        if (lday < 0) {
            endM = endM - 1;
            lday = startDayOfMonth + lday;
        }
        //处理天数问题，如：2011-01-01 到 2013-12-31  2年11个月31天     实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM + 1;
            lday = 0;
        }
        int mos = (endY - startY) * 12 + (endM - startM);
        int lyear = mos / 12;
        int lmonth = mos % 12;
        if (lyear > 0) {
            sBuilder.append(lyear + "年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth + "月");
        }
        if (!hideDayIfMonth) {
            if (lday > 0) {
                sBuilder.append(lday + "天");
            }
        } else {
            if (lyear == 0 && lmonth == 0) {
                sBuilder.append(lday + "天");
            }
        }
        return sBuilder.toString();
    }

    public static int fieldDifference(long time1, long time2, int field) {
        if (time1 == time2) {
            return 0;
        } else if (time1 > time2) {
            // 确保time1比time2小
            return fieldDifference(time2, time1, field);
        }
        // 下面清除不要参与比较的内容
        Calendar cal1 = Calendar.getInstance();
        cal1.setLenient(false);
        cal1.setTimeInMillis(time1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setLenient(false);
        cal2.setTimeInMillis(time2);
        for (int x = 0; x < Calendar.FIELD_COUNT; x++) {
            if (x > field) {
                cal1.clear(x);
                cal2.clear(x);
            }
        }
        // 重新设定初始值
        time1 = cal1.getTimeInMillis();
        time2 = cal2.getTimeInMillis();
        long ms = 0;
        int min = 0;// 下限,从0开始
        int max = 1;// 每次所加的值,第一次加1
        while (true) {
            // 因为max的值都是相对time1而言,故每次都是从time1开始而不是ms
            cal1.setTimeInMillis(time1);
            cal1.add(field, max);// 将field对应的字段加上max的值
            ms = cal1.getTimeInMillis();
            if (ms == time2) {
                // 两个时间之间相差的值为max
                min = max;
                break;
            } else if (ms > time2) {
                // 超过后,则差值肯定小于max
                break;
            } else {
                // 仍然小于time2,继续增大max,直到ms>=time2为止
                max <<= 1;
            }
        }
        // 上面的操作中没有找到准确的值,接下来使用二分查找以准确找出差值
        while (max > min) {
            cal1.setTimeInMillis(time1);
            int t = (min + max) >>> 1;
            cal1.add(field, t);
            ms = cal1.getTimeInMillis();
            if (ms == time2) {
                min = t;
                break;
            } else if (ms > time2) {
                max = t;
            } else {
                min = t;
            }
        }
        return min;
    }

    /**
     * 获取过期时间(转换成字符串显示：MM-YY-DD)
     * date为空的时候是根据当前时间算的
     * term为-1L的时候返回-1L(业务中便是无限期)
     *
     * @param term 传入的月份
     * @return
     */
    public static Long getDeadline(Long data, int term) throws Exception {
        if (-1 != term) {

            if (null == data) {
                data = System.currentTimeMillis();
            }
            if (data.longValue() < System.currentTimeMillis()) {
                data = System.currentTimeMillis();
            }
            String dateStr = DateUtil.dateLongToStr(data, DateUtil.YEAR_MONTH_DATE);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
            cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(5, 7)) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(8, 10)));
            cal.add(Calendar.MONTH, term);
            data = DateUtil.convertDateStringToDateLong(DateUtil.YEAR_MONTH_DATE,
                    DateUtil.convertDateLongToDateString(DateUtil.YEAR_MONTH_DATE, cal.getTimeInMillis()));
            return data.longValue() + 86399000L;
        }

        return -1L;
    }

    public static String getTodayStr(String format) {
        Long date = System.currentTimeMillis();
        return DateUtil.convertDateLongToDateString(format, date);
    }


    public static Long parseInquiryCenterDate(String timeStr) {
        Long time = System.currentTimeMillis();
        try {
            if (timeStr.equals("昨天")) {
                time = DateUtil.getStartTimeOfYesterday();
            } else if (timeStr.equals("上月第一天")) {
                time = DateUtil.getFirstDayOfLastMonth();
            } else if (timeStr.equals("本月第一天")) {
                time = DateUtil.getFirstDayDateTimeOfMonth();
            } else if (timeStr.equals("今年第一天")) {
                time = DateUtil.getStartOfYearOfLong();
            } else if (timeStr.equals("上月最后一天")) {
                time = DateUtil.getLastDayOfLastMonth();
            } else if (timeStr.equals("今天")) {
                time = DateUtil.getTheDayTime();
            } else {
                time = DateUtil.convertDateStringToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, timeStr);
            }
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
            time = System.currentTimeMillis();
        } finally {
            return time;
        }
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getYear(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    //判断是否是当前日期
    public static boolean isCurrentTime(Long date) throws Exception {
        if (date == null) {
            return false;
        }
        date = DateUtil.convertDateDateShortToDateLong(DATE_STRING_FORMAT_DAY, new Date(date));
        Long currentDate = DateUtil.convertDateDateShortToDateLong(DATE_STRING_FORMAT_DAY, new Date(System.currentTimeMillis()));
        return (currentDate - date == 0);
    }

    public static long getYearByVestDate(long vestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(vestDate);
        return calendar.get(Calendar.YEAR);
    }

    public static long getMonthByVestDate(long vestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(vestDate);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static long getDayByVestDate(long vestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(vestDate);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static boolean isSameDay(Long currentDate, Long compareDate) {
        try {
            if (currentDate == null || compareDate == null) {
                return false;
            }

            currentDate = DateUtil.convertDateDateShortToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, new Date(currentDate));
            compareDate = DateUtil.convertDateDateShortToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, new Date(compareDate));
            return (currentDate - compareDate == 0);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }


    public static Long get6clock(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到vestDate所在天的0时0分0秒的毫秒数
     *
     * @param vestDate
     */
    public static long getStartTimeOfTimeDay(Long vestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(vestDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 标准格式,例如： 2012-04-10
     *
     * @param sDate
     * @return
     */
    public static boolean isStandardDateFormat(String sDate) {
        if (StringUtils.isEmpty(sDate)) {
            return false;
        }
        String importedDatePattern = "^[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|1\\d|2\\d|3[0-1])$";
        Pattern pattern = Pattern.compile(importedDatePattern);
        Matcher match = pattern.matcher(sDate);
        return match.matches();
    }


    /**
     * 获得昨天零时零分零秒
     *
     * @return
     */
    public static Calendar getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Long startTime = getStartTimeOfTimeDay(calendar.getTimeInMillis());
        calendar.setTimeInMillis(startTime);
        return calendar;
    }

    public static Long getLastWeekTime(Calendar calendar) {
        calendar.add(Calendar.DATE, -7);
        return calendar.getTimeInMillis();
    }

    public static Long getLastMonthTime(Calendar calendar) {
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTimeInMillis();
    }

    public static Long getLastThreeMonthTime(Calendar calendar) {
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTimeInMillis();
    }

    public static Long getLastHalfYearTime(Calendar calendar) {
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTimeInMillis();
    }

    public static Long getLastYearTime(Calendar calendar) {
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取指定日期开始时间：00：00：00
     *
     * @return 当前天0时0分0秒
     * @throws ParseException
     */
    public static Long getStartOfDate(Long vDate, String formatStr) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(vDate));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat(formatStr);
        return convertDateStringToDateLong(formatStr, format.format(date));
    }

    /**
     * 获取指定日期结束时间23：59：59
     *
     * @return 当前天23：59：59
     * @throws ParseException
     */
    public static Long getEndOfDate(Long vDate, String formatStr) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(vDate));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat(formatStr);
        return convertDateStringToDateLong(formatStr, format.format(date));
    }

    public static Long getEndOfDate(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime();
    }

    /**
     * 7天前的开始时间
     *
     * @return
     */
    public static Long getLastWeekStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
//    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Long startTime = DateUtil.getStartTimeOfTimeDay(calendar.getTimeInMillis());
        return startTime;

    }

    public static void main(String[] args) {
        System.out.println(convertDateLongToDateString(DateUtil.ALL, getLastWeekStartTime()));
    }

    public static Long getLastWeekEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getLastWeekStartTime());
        calendar.add(Calendar.DATE, 6);
        Long startTime = DateUtil.getEndOfDate(calendar.getTimeInMillis());
        return startTime;
    }

    public static int getWeekOfYear(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    //获取当月1号0点0分0秒0毫秒
    public static Long getOneYearBeforeStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        return calendar.getTime().getTime();
    }

    //month 是实际的月份
    public static Long getDateByYearMonth(int statYear, int statMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, statYear);
        calendar.set(Calendar.MONTH, statMonth - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 根据n从当前时间推移
     *
     * @param n
     * @return
     */
    public static Long getDateByDay(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, n);
        return calendar.getTimeInMillis();
    }

//  //1000000 -> N周N天N小时N分N秒
//  //
//  public static String convertSecondTimeStr(Double travelTime) {
//    StringBuilder sb = new StringBuilder();
//    if(NumberUtils.doubleVal(travelTime) <=0){
//      return "0";
//    }
//    if(travelTime/604800>=1){
//      int week =  new Double(travelTime/604800D).intValue();
//      sb.append(week).append("周");
//      travelTime = travelTime-week*604800;
//    }
//    if(travelTime/86400>=1){
//      int day =  new Double(travelTime/86400).intValue();
//      sb.append(day).append("天");
//      travelTime = travelTime-day*86400;
//    }
//    if(travelTime/3600>=1){
//      int min =  new Double(travelTime/3600).intValue();
//      sb.append(min).append("小时");
//      travelTime = travelTime-min*3600;
//    }
//    if(travelTime/60>=1){
//      int min =  new Double(travelTime/60).intValue();
//      sb.append(min).append("分");
//      travelTime = travelTime-min*60;
//    }
//    if(travelTime>=1){
//      int min =  travelTime.intValue();
//      sb.append(min).append("秒");
//    }
//    return sb.toString();
//  }

//  //1000000 -> N周N天N小时N分N秒
//  //
//  public static String convertSecondTimeStr(Long travelTime) {
//    StringBuilder sb = new StringBuilder();
//    if(NumberUtil.longValue(travelTime) <=0){
//      return "0";
//    }
//    if(travelTime/604800>=1){
//      int week =  new Double(travelTime/604800).intValue();
//      sb.append(week).append("周");
//      travelTime = travelTime-week*604800;
//    }
//    if(travelTime/86400>=1){
//      int day =  new Double(travelTime/86400).intValue();
//      sb.append(day).append("天");
//      travelTime = travelTime-day*86400;
//    }
//    if(travelTime/3600>=1){
//      int min =  new Double(travelTime/3600).intValue();
//      sb.append(min).append("小时");
//      travelTime = travelTime-min*3600;
//    }
//    if(travelTime/60>=1){
//      int min =  new Double(travelTime/60).intValue();
//      sb.append(min).append("分");
//      travelTime = travelTime-min*60;
//    }
//    if(travelTime>=1){
//      int min =  travelTime.intValue();
//      sb.append(min).append("秒");
//    }
//    return sb.toString();
//  }

    /*
    * 取本周7天的第一天（周一的日期）
    */
    public static String getNowWeekBegin() {
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday + " 00:00:00";
    }


    public static Long getAfterNDaysDate(Long date, int n) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(Calendar.DATE, n);//天后的日期
        Date date_ = new Date(c.getTimeInMillis()); //将c转换成Date
        return DateUtil.convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", formatDate.format(date_));
    }

    public static long getTimeMillisByHourOfDay(int HourOfDay){
        TimeZone curTimeZone = TimeZone.getTimeZone("GMT+8");
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, HourOfDay);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

}
