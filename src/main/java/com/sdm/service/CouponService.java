package com.sdm.service;

import com.github.pagehelper.PageInfo;
import com.sdm.entity.Coupon;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/14 17:21
 */
public interface CouponService {

    PageInfo<Coupon> fincCouponPageList(int pi, int ps);
}
