package com.sdm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdm.dao.CategoryMapper;
import com.sdm.dao.CategoryRecommendMapper;
import com.sdm.dao.TbCategoryMapper;
import com.sdm.entity.Category;
import com.sdm.entity.CategoryRecommend;
import com.sdm.entity.TbCategory;
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

    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    @Autowired
    private CategoryRecommendMapper categoryRecommendMapper;

    /**
     *
     * @param pi
     * @param ps
     * @return
     */
    @Override
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
    @Override
    public Category findById(Integer categoryId){
        if(categoryId != null && categoryId > 0){
            return categoryMapper.selectByPrimaryKey(categoryId);
        }
        return null;
    }

    /**
     *
     * @param tbpid
     * @param pi
     * @param ps
     * @return
     */
    @Override
    public PageInfo<TbCategory> findTBCategoryPage(String q, Long tbpid, Integer rid, int pi, int ps){
        Page<TbCategory> page = PageHelper.startPage(pi, ps, true);

        TbCategory tbCategory = new TbCategory();
        tbCategory.setTbpid(tbpid);
        tbCategory.setRid(rid);
        tbCategory.setCname(q);
        List<TbCategory> tbCategoryList = tbCategoryMapper.selectListByParam(tbCategory);

        PageInfo<TbCategory> pageInfo = new PageInfo<TbCategory>(tbCategoryList);
        return pageInfo;

    }

    @Override
    public CategoryRecommend selectCategoryRecommendById(Integer rid){
        return categoryRecommendMapper.selectByPrimaryKey(rid);
    }
    @Override
    public List<CategoryRecommend> selectListAll(){
        return  categoryRecommendMapper.selectListAll();
    }

    @Override
    public TbCategory findTbCategoryByTbcid(Long tbcid){
        return tbCategoryMapper.selectByTbcid(tbcid);
    }
}
