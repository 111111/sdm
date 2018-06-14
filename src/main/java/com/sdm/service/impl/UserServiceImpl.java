package com.sdm.service.impl;

import com.sdm.dao.UserMapper;
import com.sdm.entity.User;
import com.sdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 18/5/24 16:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> listAllUser(){
        return userMapper.selectAll();
    }
}
