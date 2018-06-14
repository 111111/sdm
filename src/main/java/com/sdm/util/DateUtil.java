package com.sdm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    public static String getNow() {
        String dateNow = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateNow = formatter.format(c.getTime());
        return dateNow;
    }
    
    public static String getNowMs() {
        String dateNow = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
        dateNow = formatter.format(c.getTime());
        return dateNow;
    }

    public static String getDate() {
        return getDate("yyyyMMdd");
    }
    public static String getDate(String pattern) {
        String dateNow = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        dateNow = formatter.format(c.getTime());
        return dateNow;
    }

    public static String getDateStr(Date date, String reg) {
        if (date == null)
            return null;
        String result = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(reg);
            result = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDateStr(String reg, int day, int hours) {
        String result = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(reg);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, day);
            cal.add(Calendar.HOUR, hours);
            result = formatter.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将Date类型时间转换为字符串
     * 
     * @param date
     * @return
     */
    public static String toString(Date date) {

        String time;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        time = formater.format(date);
        return time;
    }

    public static Date getDate(int day, int hours) {
        Date date = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, day);
            cal.add(Calendar.HOUR, hours);
            date = cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateStr(Date date, int day, int hours) {
        Date date1 = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, day);
            cal.add(Calendar.HOUR, hours);
            date1 = cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }

    public static String getDateStr(String reg) {
        String result = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(reg);
            result = formatter.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date converDate(String dateStr, String reg) {
        Date result = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(reg);
            result = formatter.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date converDate(String dateStr) {
        Date result = null;
        String reg = "yyyy-MM-dd";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(reg);
            result = formatter.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected static Matcher getMatcher(String regex, String inputStr) {
        Pattern mPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);// |Pattern.DOTALL
                                                                                             // |Pattern.COMMENTS
        return mPattern.matcher(inputStr);
    }

    public static String matchDate(String str, String regex) {
        Matcher mMatcher = getMatcher(regex, str);
        String result = "";
        while (mMatcher.find()) {
            MatchResult mResult = mMatcher.toMatchResult();

            if (mResult.group(0) != null && !"".equals(mResult.group(0))) {
                result = mResult.group(0);
            }
        }
        return result;
    }

    public static Date convertCrawlerDate(String date) {
        date = date.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", " ");

        String[] regex = { "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", "\\d{4}-\\d{2}-\\d{2}", "\\d{4}\\d{2}\\d{2}",
                "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}" };
        Date datetime = null;
        String matchDate = "";

        date = date.replaceAll("更新发表", "");

        for (int i = 0; i < regex.length; i++) {
            matchDate = matchDate(date, regex[i]);
            if (matchDate != null && !"".equals(matchDate)) {
                date = matchDate;
                switch (i) {
                case 0:
                    break;
                case 1:
                    date += " 00:00";
                    break;
                case 2:
                    String year = date.substring(0, 4);
                    String month = date.substring(4, 6);
                    String day = date.substring(6, 8);

                    date = year + "-" + month + "-" + day + " 00:00";
                    break;
                default:
                    break;
                }
                datetime = converDate(date, "yyyy-MM-dd HH:mm");
                break;
            }
        }

        return datetime;
    }

    public static Date formatPubDate(int type, String date) {
        Date datetime = null;
        try {
            switch (type) {
            case 0:
                if (date != null && date.replaceAll(" ", "").length() > 0) {
                    date = date.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", " ");
                    datetime = converDate(date, "yyyy-MM-dd HH:mm");
                }
                break;
            case 1:

                // 发布时间：(2012-12-06 09:14)
                if (date != null && date.replaceAll(" ", "").length() > 0) {
                    date = date.replaceAll("发布时间：", "").replace(")", "").replaceAll("日期：", "").replace("(", "");
                    if (date.length() < 16) {
                        date += " 00:00";
                    }
                    datetime = converDate(date, "yyyy-MM-dd HH:mm");
                }
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datetime;
    }

    /**
     * 把字符串代表的日期转换为Date类型。
     * 
     * @param text
     *            yyyy-MM-dd HH:mm:ss格式的日期字符串。
     * @return
     */
    static public Date convertS2Date(String text) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return convertS2Date(text, pattern);
    }

    /**
     *
     * @param text
     * @param pattern
     * @return
     */
    static public Date convertS2Date(String text, String pattern) {
        if (text == null || "".equals(text.trim())) {
            return null;
        }
        if (text.length() < pattern.length()) {
            pattern = pattern.substring(0, text.length());
        }
        DateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(text);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * @param date
     *            日期类型
     * @param num
     *            前后几天
     * @param regx
     *            日期格式
     * @return
     */
    public static String beforeThisDate(Date date, int num, String regx) {
        String dat = "";
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, num);
            SimpleDateFormat format = new SimpleDateFormat(regx);
            dat = format.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dat;
    }

    public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }

    /**
     * 传入一个时间，去掉后面的时分秒后的Date
     * 
     * @param d
     * @return
     */
    public static Date clearDate(Date d) {
        if (d == null) {
            d = new Date();
        }
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String temp = myFormat.format(d);
        Date tempDate = new Date();
        try {
            tempDate = myFormat.parse(temp);

        } catch (Exception e) {

        }

        return tempDate;
    }

    /**
     * 判断日期大小，date1>=date2
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compare_date(String date1, String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 判断date1大余date2 多少（year）年
     * 
     * @param date1
     * @param date2
     * @param year
     * @return
     */
    public static boolean compare_date(Date date1, Date date2, int year) {

        try {
            System.out.println("date1:" + date1);
            System.out.println("date2:" + date2);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date1);
            if (year != 0) {
                rightNow.add(Calendar.YEAR, year);// 日期减N年
                date1 = rightNow.getTime();
                System.out.println("date1-year:" + date1);
            }
            if (date1.getTime() > date2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 日期转换成字符串
     * 
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成字符串
     * 
     * @param date
     * @return str
     */
    public static String DateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * 
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串日期格式转换(yyyy-MM-dd HH:mm:ss转yyyyMMddHHmmss)
     * 
     * @param str
     * @return date
     */
    public static String StrToStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        String strTime = null;
        try {
            date = format.parse(str);
            strTime = DateToString(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    /**
     * 字符串日期格式转换(yyyyMMddHHmmss转yyyy-MM-dd HH:mm:ss)
     * 
     * @param str
     * @return date
     */

    public static String StrToString(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        String strTime = null;
        try {
            date = format.parse(str);
            strTime = DateToStr(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    /**
     * 字符串日期格式转换验证(yyyy-MM-dd HH:mm:ss)
     * 
     * @param str
     * @return date
     */
    public static boolean checkDate(String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.setLenient(false);
            sdf.parse(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String args[]) {
        /*
         * String identityCardNo = "431126198902021210";
         * 
         * Date birthday = converDate(identityCardNo.substring(6, 14),
         * "yyyyMMdd"); Date joinLeagueTime = converDate("2002-02", "yyyy-MM");
         * //Date joinLeagueTime = converDate("2016-12", "yyyy-MM"); Date
         * partyTime = converDate("2007-02", "yyyy-MM"); Date volunteerTime =
         * converDate("2005-02", "yyyy-MM"); Date joinPositionTime =
         * converDate("2002-03", "yyyy-MM");
         * //System.out.println(compare_date(joinLeagueTime, birthday,-12)); if
         * (compare_date(joinLeagueTime, new Date(),0)) {
         * System.out.println("入团年月不能大于当前时间"); } if
         * (!compare_date(joinLeagueTime, birthday,-13)) {
         * System.out.println("入团年月不能小于13周岁"); }
         * 
         * if (compare_date(partyTime, new Date(),0)) {
         * System.out.println("入党年月不能大于当前时间"); } if (!compare_date(partyTime,
         * birthday,-18)) { System.out.println("入党不能小于18周岁"); }
         * 
         * 
         * if (compare_date(volunteerTime, new Date(),0)) {
         * System.out.println("成为志愿者日期不能大于当前时间"); } if
         * (!compare_date(volunteerTime, birthday,-16)) {
         * System.out.println("成为志愿者年龄不能小于16周岁"); }
         * 
         * 
         * if (compare_date(joinPositionTime, new Date(),0)) {
         * System.out.println("任现职年月不能大于当前时间"); } if
         * (!compare_date(joinPositionTime, birthday,-13)) {
         * System.out.println("任现职年月不能小于13周岁"); } if
         * (!compare_date(joinPositionTime, joinLeagueTime,0)) {
         * System.out.println("任现职年月不能小于入团年月"); }
         */
        try {
            // System.out.println(daysBetween("2016-11-02 12:22:11","2016-12-12
            // 10:11:41"));
            System.out.println(StrToString("20170719112337"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
