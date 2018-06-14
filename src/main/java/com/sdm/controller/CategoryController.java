package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.entity.Category;
import com.sdm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 2018/6/12 22:05
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/categorylist")
    public String categoryList(Model model){

        PageInfo<Category> pageInfo = categoryService.findCategoryPage(1, 200);
        model.addAttribute("pageinfo", pageInfo);

        return "category/categorylist";
    }

}
