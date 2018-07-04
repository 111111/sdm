package com.sdm.bean;

import com.taobao.api.response.TbkDgItemCouponGetResponse;

import java.math.BigDecimal;

/**
 * com.sdm.bean说明:
 * Created by qinyun
 * 2018/6/28 09:26
 */
public class TBCouponBean extends TbkDgItemCouponGetResponse.TbkCoupon{


    private BigDecimal commissionPrice;

    public BigDecimal getCommissionPrice() {
        return commissionPrice;
    }

    public void setCommissionPrice(BigDecimal commissionPrice) {
        this.commissionPrice = commissionPrice;
    }

    public void computeCommissionPrice(){

    }
}
