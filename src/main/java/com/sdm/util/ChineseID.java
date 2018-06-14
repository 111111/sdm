package com.sdm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 说明：身份证号码工具
 * Created by qinyun.
 * 2016/8/13 10:12
 */
public class ChineseID {
    private static String[] MaskCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

    /**
     * 校验身份证号码，成功返回true，失败返回false
     * @param chineseID 身份证号码
     * @return
     */
    public static boolean checkChineseID(String chineseID){
        return chineseID.endsWith(calcIDChecksum(chineseID));

    }

    /**
     * 身份证号码校验，返回身份证号码最后一位校验码，如果返回的校验码为-1或者不等于身份证号码最后一位字符，则为校验失败
     * @param chineseID 身份证号码
     * @return
     */
    public static String calcIDChecksum(String chineseID)
    {
        String retValue = "-1";
        if(chineseID == null || "".equals(chineseID) || chineseID.length() < 17){
            return retValue;
        }
        try
        {
            int sum = 0;
            sum += (Integer.parseInt(chineseID.substring(0, 1)) * 7);
            sum += (Integer.parseInt(chineseID.substring(1, 2)) * 9);
            sum += (Integer.parseInt(chineseID.substring(2, 3)) * 10);
            sum += (Integer.parseInt(chineseID.substring(3, 4)) * 5);
            sum += (Integer.parseInt(chineseID.substring(4, 5)) * 8);
            sum += (Integer.parseInt(chineseID.substring(5, 6)) * 4);
            sum += (Integer.parseInt(chineseID.substring(6, 7)) * 2);
            sum += (Integer.parseInt(chineseID.substring(7, 8)));
            sum += (Integer.parseInt(chineseID.substring(8, 9)) * 6);
            sum += (Integer.parseInt(chineseID.substring(9, 10)) * 3);

            sum += (Integer.parseInt(chineseID.substring(10, 11)) * 7);
            sum += (Integer.parseInt(chineseID.substring(11, 12)) * 9);
            sum += (Integer.parseInt(chineseID.substring(12, 13)) * 10);
            sum += (Integer.parseInt(chineseID.substring(13, 14)) * 5);
            sum += (Integer.parseInt(chineseID.substring(14, 15)) * 8);
            sum += (Integer.parseInt(chineseID.substring(15, 16)) * 4);
            sum += (Integer.parseInt(chineseID.substring(16, 17)) * 2);

            sum %= 11;

            retValue = MaskCode[sum];
        }
        catch (Exception ex)
        {
            System.out.println("ex = " + chineseID);
            ex.printStackTrace();
        }

        return retValue;
    }

    /**
     * 从身份证号码获取生日
     * @param chineseID 身份证号码
     * @return 返回生日日期
     */
    public static Date getBirthdayForChineseID(String chineseID){
        if(chineseID == null || "".equals(chineseID) || chineseID.length() < 17){
            return null;
        }

        Date birthday =  null;
        try {
            String d = chineseID.substring(6, 14);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            birthday = format.parse(d);
        } catch(Exception ex) {
            ex.printStackTrace();
        }


        return birthday;
    }

    /**
     * 从身份证号码获取性别信息
     * @return 返回1为男，0为女， -1为未知
     */
    public static int getGenderForChineseID(String chineseID){
        if(chineseID == null || "".equals(chineseID) || chineseID.length() < 17){
            return -1;
        }
        String g = chineseID.substring(16,17);
        return Integer.parseInt(g) % 2;
    }
    
    /*public static int getAge(String IDCardNum){
    	Calendar cal1 = Calendar.getInstance();
    	Calendar today = Calendar.getInstance();
    	//System.out.println(IDCardNum.substring(12,14));
    	cal1.set(Integer.parseInt(IDCardNum.substring(6,10)),
    	Integer.parseInt(IDCardNum.substring(10,12)),
    	Integer.parseInt(IDCardNum.substring(12,14)));
    	return getYearDiff(today, cal1);
}

	public static int getYearDiff(Calendar cal, Calendar cal1){
		int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
		int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
		return (y * 12 + m)/12;
	}
	*/
	
	 public static Date parse(String strDate) throws ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        return sdf.parse(strDate);  
    }  
  
    //由出生日期获得年龄  
    public static int getAge(String IDCardNum){
    	Date birthDay = null;
		try {
			birthDay = parse(IDCardNum.substring(6,14));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    } 


    public static void main(String[] args) {
       /* String chineseID = "14112119851017408";
        boolean b = checkChineseID(chineseID);
        System.out.println("b = " + b);
        Date d = getBirthdayForChineseID(chineseID);
        System.out.println("d = " + d);
        int g = getGenderForChineseID(chineseID);
        System.out.println("g = " + g);*/
        System.out.println(getAge("110101198812079337"));
    }
    
    
    
	
}
