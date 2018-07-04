package com.sdm.cache;

import com.sdm.constant.SysConfigConstant;
import com.sdm.entity.SysConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.sdm.cache说明:
 * Created by qinyun
 * 2018/6/22 14:51
 */
public class SysConfigCache {

    private static List<SysConfig> sysConfigList;

    private static Map<String, String> sysConfigMap = new HashMap<>();

    /**
     *
     * @param list
     */
    public static void putCache(List<SysConfig> list){
        sysConfigList = list;
        if(sysConfigMap == null){
            sysConfigMap = new HashMap<>();
        }
        sysConfigMap.clear();
        for(SysConfig sysConfig : sysConfigList){
            sysConfigMap.put(sysConfig.getParamname(), sysConfig.getParamvalue());
        }

    }

    /**
     *
     * @param paramname
     * @return
     */
    private static String getParamvalue(String paramname){
        return sysConfigMap == null ? null : sysConfigMap.get(paramname);
    }

    public static String getTBApiUrl(){
        return getParamvalue(SysConfigConstant.TB_API_URL);
    }

    public static String getTBAppkey(){
        return getParamvalue(SysConfigConstant.TB_APP_KEY);
    }

    public static String getTBSecret(){
        return getParamvalue(SysConfigConstant.TB_APP_SECRET);
    }

    public static String getTBPid(){
        return getParamvalue(SysConfigConstant.TB_PID);
    }

    public static String getTBUserid(){
        return getParamvalue(SysConfigConstant.TB_USERID);
    }

    /**
     *
     * @return
     */
    public static Long getTBAdzoneId(){
        String pid = getTBPid();
        String adzoneId = pid.split("_")[3];
        return Long.parseLong(adzoneId);

    }


}
