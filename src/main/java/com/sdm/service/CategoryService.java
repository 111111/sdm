package com.sdm.service;

import com.github.pagehelper.PageInfo;
import com.sdm.entity.Category;
import com.sdm.entity.CategoryRecommend;
import com.sdm.entity.TbCategory;

import java.util.List;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/12 22:06
 */
public interface CategoryService {

    PageInfo<Category> findCategoryPage(int pi, int ps);

    Category findById(Integer categoryId);

    PageInfo<TbCategory> findTBCategoryPage(String q, Long tbpid, Integer rid, int pi, int ps);

    CategoryRecommend selectCategoryRecommendById(Integer rid);

    List<CategoryRecommend> selectListAll();

    TbCategory findTbCategoryByTbcid(Long tbcid);

}
