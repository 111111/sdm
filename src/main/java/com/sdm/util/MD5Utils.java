package com.sdm.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils
{

    public MD5Utils()
    {
    }
    public static String encode(byte[] value) {
		if (value == null) {
			return null;
		}
		return DigestUtils.md5DigestAsHex(value);
	}

    public static String md5(byte b[])
        throws NoSuchAlgorithmException
    {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(b, 0, b.length);
        return byteArrayToHexString(md5.digest());
    }

    public static String md5(String data)
        throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte b[] = data.getBytes("UTF8");
        md5.update(b, 0, b.length);
        return byteArrayToHexString(md5.digest());
    }
    
    public static String md5Half(String data){
      try{
        return md5(data).substring(0, 16);
      }catch(Throwable t){
        t.printStackTrace();
        return null;
      }
    }

    private static String byteArrayToHexString(byte b[])
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i++)
            sb.append(byteToHexString(b[i]));

        return sb.toString();
    }

    private static String byteToHexString(byte b)
    {
        int n = b;
        if(n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String hexDigits[] = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "a", "b", "c", "d", "e", "f"
    };

    public static void main(String[] args) throws Exception {
    	String md = encode("test".getBytes("utf-8"));
    	String md2 = md5(md5Half("123456"));
    	System.out.println(md);
    	System.out.println(md2);
	}
}