package com.sdm.controller;

import com.sdm.entity.ThirdUser;
import com.sdm.service.RedisService;
import com.sdm.service.UserService;
import com.sdm.util.Aes128CBC;
import com.sdm.wx.Code2sessionResponse;
import com.sdm.wx.WXUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 2018/7/9 11:35
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @RequestMapping("/wxchecksession")
    @ResponseBody
    public Object wxCheckSession(String sessionId){
        Map reMap = new HashMap();
        //默认无效
        reMap.put("recode", 101);
        reMap.put("sessionId", sessionId);
        if(sessionId != null && !"".equals(sessionId.trim())){
            Map sessionMap = userService.wxCheckSession(sessionId);
            if(sessionMap != null && sessionMap.get("user") != null){
                //sessionId有效
                reMap.put("recode", 100);
                reMap.put("user", sessionMap.get("user"));
                reMap.put("sessionId", sessionMap.get("sessionId"));
            }
        }

        return reMap;
    }

    @RequestMapping("/wxlogin")
    @ResponseBody
    public Object wxLogin(String code, String encryptedData, String iv){

       Map reMap = new HashMap();

       Map map = userService.wxLogin(code, encryptedData, iv);
       Integer recode = (Integer) map.get("recode");
        reMap.put("recode", recode);
       if(recode == 100){
           reMap.put("user", map.get("user"));
           reMap.put("sessionId", map.get("sessionId"));
       }

       return reMap;
    }
}
