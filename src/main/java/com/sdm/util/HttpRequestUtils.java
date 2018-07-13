package com.sdm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.function.Function;


/**
 * Created by I on 2017/12/1.
 */
public class HttpRequestUtils {

    public static final String CHARSET = "UTF-8";

    private static Logger logger = LogManager.getLogger(HttpRequestUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private HttpRequestUtils() {
        throw new AssertionError("Can not instantiate the instance of the class HttpRequestUtils");
    }


    public static <T, W> T sendByHttpPost(String requestUrl, W requestForm, Function<String, T> function) {
        try {
            T t = HttpRequestUtils.defaultSendByHttpPost(requestUrl, requestForm, function);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * 通过post方式，以http途径发送请求给 check异常转runtime异常
     *
     * @param requestUrl
     * @param requestForm
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T, W> T sendByHttpPost(String requestUrl, W requestForm, Class<T> tClass) {
        try {
            T t = HttpRequestUtils.defaultSendByHttpPost(requestUrl, requestForm, new Function<String, T>() {
                @Override
                public T apply(String s) {
                    try {
                        return objectMapper.readValue(s, tClass);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * 通过post方式，以http途径发送请求给
     *
     * @param requestUrl 请求地址
     * @param requestForm       请求参数.符合java bean规范
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T, W> T defaultSendByHttpPost(String requestUrl, W requestForm, Function<String, T> function) throws IOException {
        PrintWriter out;
        BufferedReader in;
        String paramsStr = toHttpContentParams(requestForm);
        logger.debug("Request: " + requestUrl + (paramsStr != null ? "?" + paramsStr : ""));
        URLConnection urlConn = new URL(requestUrl).openConnection();
        urlConn.setRequestProperty("accept", "*/*");
        urlConn.setRequestProperty("connection", "Keep-Alive");
        urlConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        out = new PrintWriter(urlConn.getOutputStream());
        // 发送请求参数flush输出流的缓冲
        if (paramsStr != null) {
            out.append(paramsStr);
        }
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), CHARSET));
        String result = "";
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        logger.debug("Response: " + result);
        T t = function.apply(result);
        return t;
    }


    /**
     * 转换javabean为http请求体<br/>
     * For example: javaBean{ name:"xiaoming",age:18,email="xi@qq.com"} ->  name=xiaoming&age=18&email=xi@qq.com
     *
     * @param o
     * @return
     */
    public static String toHttpContentParams(Object o) {
        if (o == null) {
            return null;
        }
        Class<?> aClass = o.getClass();
        Method[] methods = aClass.getMethods();
        StringBuilder httpParamsStr = new StringBuilder(methods.length * 8);
        boolean isFirstValid = true;
        for (Method m : methods) {
            String getStr = "get";
            if (m.getName().startsWith(getStr) && m.getReturnType() != Void.class && m.getParameters().length == 0 && m.getDeclaringClass() != Object.class) {
                if (!isFirstValid) {
                    httpParamsStr.append("&");
                } else {
                    isFirstValid = false;
                }
                String mn = m.getName().substring(getStr.length());
                if (mn.length() == 1) {
                    mn = mn.toLowerCase();
                } else {
                    char m1 = mn.charAt(0), m2 = mn.charAt(1);
                    if (Character.isUpperCase(m1) && Character.isLowerCase(m2)) {
                        mn = Character.toLowerCase(m1) + mn.substring(1);
                    }
                }
                Object reVal = null;
                try {
                    reVal = m.invoke(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                try {
                    String paramName = URLEncoder.encode(mn, CHARSET);
                    String paramValue = URLEncoder.encode(reVal instanceof CharSequence ? reVal.toString() : objectMapper.writeValueAsString(reVal), CHARSET);
                    httpParamsStr.append(paramName + "=" + paramValue);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpParamsStr.toString();
    }
}
