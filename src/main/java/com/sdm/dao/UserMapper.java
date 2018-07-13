package com.sdm.dao;

import com.sdm.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByParam(User record);

    User selectLogin(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}