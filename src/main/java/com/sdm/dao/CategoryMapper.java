package com.sdm.dao;

import com.sdm.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    Category selectByParameter(Category category);

    List<Category> selectListByParameter(Category category);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}