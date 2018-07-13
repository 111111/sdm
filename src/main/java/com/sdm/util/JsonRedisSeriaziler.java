package com.sdm.util;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdm.bean.WxEncryptedDataBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明： Created by qinyun. 2016/9/9 15:51
 */
public class JsonRedisSeriaziler {
    public static final String EMPTY_JSON = "{}";

    protected static ObjectMapper objectMapper = new ObjectMapper();

    private JsonRedisSeriaziler() {
    }

    /**
     * java-object as json-string
     * 
     * @param object
     * @return
     */
    public static String seriazileAsString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * json-string to java-object
     * 
     * @param str
     * @return
     */
    public static <T> T deserializeAsObject(String str, Class<T> clazz) {
        if (str == null || clazz == null) {
            return null;
        }
        try {
            //忽略json有，bean没有的字段
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(str, clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Map deserializeAsMap(String str, Class<?> keyClazz, Class<?> valueClazz) {
        if (str == null) {
            return null;
        }
        try {
            JavaType javaType = getCollectionType(HashMap.class, keyClazz, valueClazz);
            return objectMapper.readValue(str, javaType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List deserializeAsList(String str, Class<?> objClazz) {
        if (str == null) {
            return null;
        }
        try {
            JavaType javaType = getCollectionType(ArrayList.class, objClazz);
            return objectMapper.readValue(str,  javaType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
      * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
      * @param elementClasses 元素类
      * @return JavaType Java类型
    * @since 1.0
      */
     public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
         return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
     }

    public static void main(String[] args) {

         String json = "{\"openId\":\"oqwB75cxrxskSLA7cFbJOzl7rRHc\",\"nickName\":\"☁️云☁️\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Shenzhen\",\"province\":\"Guangdong\",\"country\":\"China\",\"avatarUrl\":\"https://wx.qlogo.cn/mmopen/vi_32/dhBuZzkFztLL52wapdICb2ccoHGmbIb72KiaJl0jslTmGnlM73e9zPmf7Pr63Ay9qkG5ftc31WnzMKXPf2e6Psw/132\",\"unionId\":\"oVAt_1rkQJLE5Oe1drMwwviurl7E\",\"watermark\":{\"timestamp\":1531209139,\"appid\":\"wx82630aab6a8a5ed4\"}}";
        WxEncryptedDataBean bean = deserializeAsObject(json, WxEncryptedDataBean.class);
        System.out.println("bean = " + bean);

        // System.out.println("s2 = " +
        // deserializeAsObject(seriazileAsString(s), String.class));

    }
}
