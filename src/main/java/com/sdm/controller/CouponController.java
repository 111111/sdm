package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.entity.Coupon;
import com.sdm.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
