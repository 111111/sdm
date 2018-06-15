package com.sdm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdm.dao.CouponMapper;
import com.sdm.entity.Coupon;
import com.sdm.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 2018/6/14 17:21
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;


    public PageInfo<Coupon> fincCouponPageList(int pi, int ps){
        Page<Coupon> page = PageHelper.startPage(pi, ps, true);

        Coupon param = new Coupon();
        List<Coupon> couponList = couponMapper.selectByParamter(param);
        PageInfo<Coupon> pageInfo = new PageInfo<Coupon>(couponList);
        return pageInfo;
    }
}
