package com.sdm.service.impl;

import com.sdm.bean.WxEncryptedDataBean;
import com.sdm.constant.RedisConstant;
import com.sdm.controller.LoginController;
import com.sdm.dao.ThirdUserMapper;
import com.sdm.dao.UserMapper;
import com.sdm.entity.ThirdUser;
import com.sdm.entity.User;
import com.sdm.service.RedisService;
import com.sdm.service.UserService;
import com.sdm.util.Aes128CBC;
import com.sdm.util.JsonRedisSeriaziler;
import com.sdm.util.MD5Utils;
import com.sdm.util.StringUtils;
import com.sdm.wx.Code2sessionResponse;
import com.sdm.wx.WXUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 18/5/24 16:14
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ThirdUserMapper thirdUserMapper;

    @Autowired
    private RedisService redisService;

    public Map register(String openid, String content){

        return null;

    }


    @Override
    public Map wxCheckSession(String sessionId){
        String key = RedisConstant.SESSIONKEY + sessionId;
        Map sessionMap = redisService.get(key, Map.class);
        return sessionMap;
    }
    /**
     *
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    @Override
    public Map wxLogin(String code, String encryptedData, String iv) {
        Map reMap = new HashMap();
        if(StringUtils.isNUll(code) || StringUtils.isNUll(encryptedData) || StringUtils.isNUll(iv)){
            reMap.put("recode", 101);
            return reMap;
        }
        reMap.put("recode", 100);
        String sessionId = StringUtils.getUUID();
        reMap.put("sessionId", sessionId);
        Code2sessionResponse code2sessionResponse = WXUtils.jscode2session(code);
        reMap.put("code2sessionResponse", code2sessionResponse);
        if (code2sessionResponse.getSession_key() != null && !"".equals(code2sessionResponse.getSession_key())) {
            String openid = code2sessionResponse.getOpenid();
            reMap.put("openid", openid);
            ThirdUser thirdUser = thirdUserMapper.selectByOpenid(openid);
            User user = null;
            if (thirdUser == null) {
                try {

                    String jsonStr = Aes128CBC.getInstance().decrypt2(encryptedData, code2sessionResponse.getSession_key(), iv);
                    logger.info("encryptedData = {}", jsonStr);
                    WxEncryptedDataBean wxEncryptedDataBean = JsonRedisSeriaziler.deserializeAsObject(jsonStr, WxEncryptedDataBean.class);
                    reMap.put("wxEncryptedDataBean", wxEncryptedDataBean);

                    Date now = new Date();

                    user = new User();
                    user.setCreatetime(now);
                    user.setUpdatetime(now);
                    user.setNickname(wxEncryptedDataBean.getNickName());
                    user.setHeaderurl(wxEncryptedDataBean.getAvatarUrl());

                    userMapper.insertSelective(user);

                    thirdUser = new ThirdUser();
                    thirdUser.setUserid(user.getId());
                    thirdUser.setOpenid(openid);
                    thirdUser.setType(1);
                    thirdUser.setContent(jsonStr);
                    thirdUser.setCreatetime(now);
                    thirdUser.setUpdatetime(now);
                    thirdUserMapper.insertSelective(thirdUser);


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                user = userMapper.selectByPrimaryKey(thirdUser.getUserid());
            }
            reMap.put("user", user);
        }
        String key = RedisConstant.SESSIONKEY + sessionId;
        redisService.set(key, reMap);
        logger.info("reMap={}", reMap, 60 * 60 * 24);
        return reMap;
    }

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public Map login(String userName, String password){
        Map reMap = new HashMap();
        if(userName == null || "".equals(userName.trim()) || password == null || "".equals(password.trim())){
            reMap.put("code", 110);
            reMap.put("msg", "用户名/密码不能为空.");
        }

        User user = userMapper.selectLogin(userName);
        if(user != null){
            String md5Password = MD5Utils.md5Password(password);
            if(!md5Password.equals(user.getPassword())){
                reMap.put("code", 102);
                reMap.put("msg", "密码错误.");
            }else{
                reMap.put("code", 100);
                reMap.put("msg", "登录成功.");
                reMap.put("user", user);
            }
        }else{
            reMap.put("code", 101);
            reMap.put("msg", "没有该用户.");
        }
        return reMap;
    }

    /**
     *
     * @param openid
     * @return
     */
    public Map login(String openid){
        Map reMap = new HashMap();
        if(openid == null || "".equals(openid.trim())){
            reMap.put("code", 110);
            reMap.put("msg", "openid不能为空.");
        }
        ThirdUser thirdUser = thirdUserMapper.selectByOpenid(openid);
        if(thirdUser != null){
            Integer userid = thirdUser.getUserid();
            User user = userMapper.selectByPrimaryKey(userid);
            if(user != null){
                reMap.put("code", 100);
                reMap.put("msg", "登录成功.");
                reMap.put("user", user);
                reMap.put("thirdUser", thirdUser);
            }else{
                reMap.put("code", 101);
                reMap.put("msg", "没有该用户.");
            }
        }else {
            reMap.put("code", 120);
            reMap.put("msg", "openid不存在.");
        }

        return reMap;
    }



    public User findUserById(Integer userid){
        return userMapper.selectByPrimaryKey(userid);
    }




}
