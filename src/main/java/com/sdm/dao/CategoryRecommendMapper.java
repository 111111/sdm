package com.sdm.dao;

import com.sdm.entity.CategoryRecommend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryRecommend record);

    int insertSelective(CategoryRecommend record);

    CategoryRecommend selectByPrimaryKey(Integer id);

    List<CategoryRecommend> selectListAll();

    int updateByPrimaryKeySelective(CategoryRecommend record);

    int updateByPrimaryKey(CategoryRecommend record);
}