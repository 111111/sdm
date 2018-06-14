package com.sdm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdm.dao.CategoryMapper;
import com.sdm.entity.Category;
import com.sdm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 2018/6/12 22:06
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *
     * @param pi
     * @param ps
     * @return
     */
    public PageInfo<Category> findCategoryPage(int pi, int ps){
        Page<Category> page = PageHelper.startPage(pi, ps, true);

        Category param = new Category();
        List<Category> categoryList = categoryMapper.selectListByParameter(param);

        PageInfo<Category> pageInfo = new PageInfo<Category>(categoryList);
        return pageInfo;

    }

    /**
     *
     * @param categoryId
     * @return
     */
    public Category findById(Integer categoryId){
        if(categoryId != null && categoryId > 0){
            return categoryMapper.selectByPrimaryKey(categoryId);
        }
        return null;
    }
}
