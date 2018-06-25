package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.cache.SysConfigCache;
import com.sdm.constant.SysConfigConstant;
import com.sdm.entity.Goods;
import com.sdm.entity.User;
import com.sdm.service.GoodsService;
import com.sdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 18/5/24 16:11
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/index.html")
    public String index(Model model) {
        return home(model);
    }
    @RequestMapping("/")
    public String home(Model model) {

        List<Goods> goodsList = goodsService.getGoodPage(1, 18);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);

        model.addAttribute("goodsList", goodsList);
        model.addAttribute("pageinfo", pageInfo);
        return "index/index";
    }
}
