package com.sdm.wx;

import com.sdm.cache.SysConfigCache;
import com.sdm.constant.SysConfigConstant;
import com.sdm.entity.SysConfig;
import com.sdm.util.HttpRequestUtils;

/**
 * com.sdm.wx说明:
 * Created by qinyun
 * 2018/7/9 17:16
 */
public class WXUtils {

    public static Code2sessionResponse jscode2session(String code){
        Code2sessionRequest request = new Code2sessionRequest();
//        request.setAppid("wx82630aab6a8a5ed4");
//        request.setSecret("c5d3740c255f60d044f9e60f3b23f084");
        request.setAppid(SysConfigCache.getWxAppid());
        request.setSecret(SysConfigCache.getWxSecret());
        request.setJs_code(code);

        Code2sessionResponse response = HttpRequestUtils.sendByHttpPost(SysConfigConstant.WX_JSCODE2SESSION_URL, request, Code2sessionResponse.class);

        System.out.println("response = " + response);
        return response;
    }

    public static void main(String[] args){
        String code = "023xPPGL0wkiB425tDIL09G1HL0xPPGS";
        jscode2session(code);
        //oqwB75cxrxskSLA7cFbJOzl7rRHc
        //oqwB75cxrxskSLA7cFbJOzl7rRHc

        //Q2NVSt1zmsGA7utGtzMBag==
    }
}
