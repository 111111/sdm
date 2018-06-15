package com.sdm.dao;

import com.sdm.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Long id);

    Coupon selectByGoodsidAndCouponId(Coupon coupon);

    Coupon selectByCouponId(String couponId);

    List<Coupon> selectByGoodsId(String goodsId);

    List<Coupon> selectByTbId(String tbIs);

    List<Coupon> selectByParamter(Coupon coupon);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}