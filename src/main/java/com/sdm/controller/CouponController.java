package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.bean.TBCouponBean;
import com.sdm.entity.Coupon;
import com.sdm.service.CouponService;
import com.sdm.service.TBService;
import com.sdm.util.TBUtils;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 2018/6/14 17:34
 */
@Controller
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private TBService tbService;


    @RequestMapping("/tbcouponlist")
    public String tbCcuponList(Long pi, Long platform, String searchName, Model model){
        String cat = null;
        if(platform == null || platform <= 0){
            platform = 1L;
        }
        if(pi == null || pi <= 0){
            pi = 1L;
        }
        List<TbkDgItemCouponGetResponse.TbkCoupon> tbkCouponList = tbService.getCouponList(platform, cat, searchName, 20L, pi);
        model.addAttribute("tbkCouponList", tbkCouponList);

        model.addAttribute("cat", cat);
        model.addAttribute("platform", platform);
        model.addAttribute("searchName", searchName);

        return "coupon/tbcouponlist";
    }

    @RequestMapping("/tbcouponlistappend")
    public String tbCcuponListappend(Long pi, Long platform , String searchName, Model model){
        String cat = null;
        if(platform == null || platform <= 0){
            platform = 1L;
        }
        if(pi == null || pi <= 0){
            pi = 1L;
        }
        List<TbkDgItemCouponGetResponse.TbkCoupon> tbkCouponList = tbService.getCouponList(platform, cat, searchName, 20L, pi);
        model.addAttribute("tbkCouponList", tbkCouponList);

        model.addAttribute("cat", cat);
        model.addAttribute("platform", platform);
        model.addAttribute("searchName", searchName);
        return "coupon/tbcouponlistappend";
    }


    /**
     *
     * @param discountType
     * @param pi
     * @param categoryid
     * @param model
     * @return
     */
    @RequestMapping("/couponlist")
    public String couponList(Integer discountType, Integer pi, Integer categoryid, Model model){

        if (pi == null || pi < 0){
             pi = 1;
        }

        PageInfo<Coupon> pageInfo = couponService.fincCouponPageList(pi, 20);

        model.addAttribute("pageinfo", pageInfo);

        return "coupon/couponlist";

    }

    @RequestMapping("/couponlistjson")
    @ResponseBody
    public Object couponListJson(Integer discountType, Integer pi, Integer categoryid, Model model){

        if (pi == null || pi < 0){
            pi = 1;
        }

        PageInfo<Coupon> pageInfo = couponService.fincCouponPageList(pi, 20);

        return pageInfo;

    }
}
