package com.sdm.service;

import com.sdm.entity.User;

import java.util.List;
import java.util.Map;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 18/5/24 16:14
 */
public interface UserService {

    Map wxCheckSession(String sessionId);

    Map wxLogin(String code, String encryptedData, String iv);

}
