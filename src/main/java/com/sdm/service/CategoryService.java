package com.sdm.service;

import com.github.pagehelper.PageInfo;
import com.sdm.entity.Category;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/12 22:06
 */
public interface CategoryService {

    PageInfo<Category> findCategoryPage(int pi, int ps);

    Category findById(Integer categoryId);

}
