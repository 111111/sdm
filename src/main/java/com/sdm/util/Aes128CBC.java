package com.sdm.util;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * com.sdm.util说明:
 * Created by qinyun
 * 2018/7/9 23:09
 */
public class Aes128CBC {

    /*
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
//    private static String sKey="sklhdflsjfsdgdeg";
//    private static String ivParameter="cfbsdfgsdfxccvd1";
    private static Aes128CBC instance=null;
    private Aes128CBC(){

    }
    public static Aes128CBC getInstance(){
        if (instance==null) {
            instance = new Aes128CBC();
        }
        return instance;
    }
    // 算法名称
    final String KEY_ALGORITHM = "AES";

    // 加解密算法/模式/填充方式
    final String algorithmStr = "AES/CBC/PKCS7Padding";
    //
    private Key key;
    private Cipher cipher;

    private static org.bouncycastle.jce.provider.BouncyCastleProvider bouncyCastleProvider = null;

    public static synchronized org.bouncycastle.jce.provider.BouncyCastleProvider getInstanceBouncyCastleProvider() {
        if (bouncyCastleProvider == null) {
            bouncyCastleProvider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        }
        return bouncyCastleProvider;
    }

    public void init(byte[] keyBytes) {

        // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(getInstanceBouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedDataStr
     *            要解密的字符串
     * @param keyBytesStr
     *            解密密钥
     * @return
     */
    public byte[] decrypt(String encryptedDataStr, String keyBytesStr, String ivStr) {
        byte[] encryptedText = null;
        byte[] encryptedData = null;
        byte[] sessionkey = null;
        byte[] iv = null;

        try {
            sessionkey = Base64.decodeBase64(keyBytesStr);
            encryptedData = Base64.decodeBase64(encryptedDataStr);
            iv = Base64.decodeBase64(ivStr);

            init(sessionkey);

            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }
    public String decrypt2(String encryptedDataStr, String keyBytesStr, String ivStr) {
        byte[] data = Aes128CBC.getInstance().decrypt(encryptedDataStr, keyBytesStr, ivStr);
        String deString = null;
        try{
            deString = new String(data,"utf-8");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {

        }
        return deString;
    }

    public static void main(String[] args) throws Exception {

        String sKey = "x98Oo96iXsUNKu7eXcsS+w==";
        String ivParameter = "j+EexOYCPhLhEALNd1vABg==";

        String es = "ZtVJuTCxaJXEdc9yqE1pROsTSB+fN2rgCeTI53OtAK3RKwWIVDkia5u3p38PjiV3D/ScShd9qBymt3OtcPkyrd/us+jdjzXJxSzBFoxKXWTNRp7ZPBm3rfI/hSUZAJpN7EsYL043IAYF/fICnn6ERlIzIwStWLOEDTHRRQG7+mCTlcGJRbSoPreD2n3Q2qg+yytHFFlBtz4uSR3kf6z/W5mgT2ecQo9SctqEa65nvyqd8LAarE9VEthrWwCpJIhaNvxU8nasP6ctShIo2pyoC3RTbycgkPTykapi+zfsfFrQJSWi7Wu9wVDox4of2lV8j1byQWpUfqrdnH+hd7NFfLwtMiGTnqyUw6syRDhG4iTimdO1++prOssefDxNjD4cMjuV2Mx3AwY0YM8UqSLcKOj3281B73N1tGk0WXUazgwZT3fSkpuz8O7g4BbpZOCO63I57Z7NNWUoL9oXCMZvV4SLro8XqKX65yPwPrV2zJHKOYwqlc+zjkg0obXGe9zteU7Ge84apeUKUDrDi4Rm7IoAhca3G5rYQ30oWSCIYIk=";

        // 需要加密的字串
//        String cSrc = "我来自中国";
//        System.out.println(cSrc);
        // 加密
        long lStart = System.currentTimeMillis();
//        String enString = Aes128CBC.getInstance().encrypt(cSrc, "utf-8", sKey, ivParameter);
//        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
         byte[] data = Aes128CBC.getInstance().decrypt(es, sKey, ivParameter);
        String DeString = new String(data,"utf-8");
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }
}
