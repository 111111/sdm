package com.sdm.service.impl;

import com.sdm.dao.TbCategoryMapper;
import com.sdm.entity.TbCategory;
import com.sdm.service.TbCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 2018/6/28 23:52
 */
@Service
public class TbCategoryServiceImpl implements TbCategoryService {

    private Logger logger = LoggerFactory.getLogger(TbCategoryServiceImpl.class);

    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    /**
     *
     * @param category
     */
    @Async
    public void insert(TbCategory category){

        TbCategory tbCategory = tbCategoryMapper.selectByTbcid(category.getTbcid());
        if(tbCategory == null){
            tbCategoryMapper.insertSelective(category);
            logger.debug("category is null, new category=", category);
        }else{
            logger.debug("category is exists.old tbCategory=", tbCategory);
        }
    }
}
