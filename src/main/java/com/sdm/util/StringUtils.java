package com.sdm.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：
 * Created by qinyun.
 * 2018/3/21 16:06
 */
public class StringUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static int getRandomInt(int min, int max){
        Random rd = new Random();
        int rt = rd.nextInt(max - min + 1) + min;
        return rt;
    }
    public static Double getRandomDouble(Double min, Double max){
        Random rd = new Random();
        Double rt = min + ((max - min) * rd.nextDouble());
        BigDecimal bg = new BigDecimal(rt);

        return  bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static int nullToInteger(String value) {
        int ret = 0;
        try {
            ret = (value == null || "".equals(value)) ? 0 : Integer.parseInt(value.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static int nullToInteger(String value, int def) {
        int ret = def;
        try {
            ret = (value == null) ? def : Integer.parseInt(value.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static Long nullToLong(String value) {
        long ret = 0;
        try {
            ret = (value == null) ? null : Long.parseLong(value.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static Long nullToLong(String value, Long def) {
        long ret = def;
        try {
            ret = (value == null) ? def : Long.parseLong(value.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 判断字符串是否为空，为空返回true，不为空返回false
     *
     * @param str
     * @return
     */
    public static boolean isNUll(String str) {
        return str == null || "".equals(str.trim());
    }

    public static void main(String...args){
//        for(int i = 0; i < 500; i++){
//            System.out.println("i = " + getRandomDouble(0.1, 20.0));
//        }
//        String str0 = "北京市（92所）";
//        String p = str0.substring(0, str0.indexOf("（"));
//        System.out.println("p = " + p);
        //IP,datetime,”referrer”,”url”,userid, “user_agent”
        String str1 = "满103元减5元" ;
        String fullAmountStr = str1.substring(str1.indexOf("满") + 1, str1.indexOf("元"));
        System.out.println("fullAmountStr = " + fullAmountStr);
        String preferentialAmount = str1.substring(str1.indexOf("减") + 1, str1.lastIndexOf("元"));
        System.out.println("preferentialAmount = " + preferentialAmount);

    }

}
