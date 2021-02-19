package org.b3log.solo.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取日期在当年为第几周
     * @param datetime
     * @return
     * @throws ParseException
     */
    public static Integer whatWeek(String datetime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.THURSDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 判断一年的第几周
     * @param datetime
     * @return
     * @throws ParseException
     */
    public static Integer year(String datetime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static String yesterday() {
        return format(minusDays(new Date(), 1), "yyyy-MM-dd");
    }

    public static String yesterdayForYyMm() {
        return format(minusDays(new Date(), 1), "yyyy-MM");
    }

    public static String todayDd() {
        return format(minusDays(new Date(), 1), "dd");
    }

    public static String format(Date date, String pattern) {
        return ConcurrentDateFormat.of(pattern).format(date);
    }

    public static Date minusDays(Date date, long days) {
        return minus(date, Duration.ofDays(days));
    }

    public static Date minus(Date date, TemporalAmount amount) {
        Instant instant = date.toInstant();
        return Date.from(instant.minus(amount));
    }

    public static String[] getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] date = new String[2];
        if (Integer.parseInt(todayDd()) == 1) {
            Calendar calendar5 = Calendar.getInstance();
            int maxCurrentMonthDay = 0;
            maxCurrentMonthDay = calendar5.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar5.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
            calendar5.set(Calendar.DAY_OF_MONTH, 1);
            date[0] = sdf.format(calendar5.getTime());
        } else {
            //当月一号
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.DAY_OF_MONTH, 1);
            date[0] = sdf.format(calendar1.getTime());
        }
        date[1] = yesterday();
        return date;
    }


    public static long toMilliseconds(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long toMilliseconds(final String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString);
        return date.getTime();
    }

    public static Date StringToDate(final String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    public static long toMilliseconds(LocalDate localDate) {
        return toMilliseconds(localDate.atStartOfDay());
    }

    public static String getLastMonthOneDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }

    public static String getYestDayMonthOneDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(minusDays(new Date(), 1));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format(calendar.getTime(),"yyyy-MM-dd");
    }

    public static String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return format.format(calendar.getTime());
    }

    public static String getLastMonthLastDay() {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sf.format(calendar.getTime());
    }

    public static String today() {
        return format(new Date(), "yyyyMMdd");
    }
    public static void main(String[] args) throws ParseException {
        System.out.println(whatWeek("2021-02-03"));
        System.out.println(year("2021-02-03"));
    }
}
