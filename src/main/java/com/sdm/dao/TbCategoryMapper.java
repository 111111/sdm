package com.sdm.dao;

import com.sdm.entity.TbCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCategory record);

    int insertSelective(TbCategory record);

    TbCategory selectByPrimaryKey(Integer id);

    TbCategory selectByTbcid(Long tbcid);

    List<TbCategory> selectListByParam(TbCategory record);

    int updateByPrimaryKeySelective(TbCategory record);

    int updateByPrimaryKey(TbCategory record);
}