package com.sdm.bean;

import com.taobao.api.response.TbkItemInfoGetResponse;

import java.math.BigDecimal;

/**
 * com.sdm.bean说明:
 * Created by qinyun
 * 2018/7/1 01:40
 */
public class NTbkItemBean extends TbkItemInfoGetResponse.NTbkItem{

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
