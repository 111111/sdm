package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.ApplicationTests;
import com.sdm.entity.Goods;
import com.sdm.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 2019/3/12 23:32
 */
public class HomeControllerTest extends ApplicationTests {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void index() {
        List<Goods> goodsList = goodsService.getGoodPage(1, 18);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        assertNotNull(pageInfo.getList());

    }

    @Test
    public void home() {
    }
}