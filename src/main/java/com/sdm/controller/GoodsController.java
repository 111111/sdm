package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.config.SysConfigProp;
import com.sdm.entity.Category;
import com.sdm.entity.Goods;
import com.sdm.service.CategoryService;
import com.sdm.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 18/5/28 16:42
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private SysConfigProp sysConfigProp;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;



    @RequestMapping("/goodslist")
    public String goodslist(String searchName, Integer pi,Integer categoryid, Model model){
        if(pi == null || pi < 1){
            pi = 1;
        }
        if(searchName == null){
            searchName = "";
        }

        if(categoryid == null){
            categoryid = 0;
        }
        List<Goods> goodsList = goodsService.getGoodPage(searchName, categoryid, pi, 18);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);

        model.addAttribute("goodsList", goodsList);
        model.addAttribute("pageinfo", pageInfo);
        model.addAttribute("searchName", searchName);
        model.addAttribute("pi", pi);
        model.addAttribute("categoryid", categoryid);
        if(categoryid > 0){
            Category category = categoryService.findById(categoryid);
            model.addAttribute("category", category);
        }

        return "goods/goodslist";
    }

    @RequestMapping("/goodslistjson")
    @ResponseBody
    public Object goodslistjson(String searchName,Integer categoryid, Integer pi){
        if(pi == null || pi < 1){
            pi = 1;
        }
        if(searchName == null){
            searchName = "";
        }

        List<Goods> goodsList = goodsService.getGoodPage(searchName, categoryid,pi, 18);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        return  pageInfo;
    }




}
