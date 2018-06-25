package com.sdm.service;

import com.taobao.api.response.TbkDgItemCouponGetResponse;

import java.util.List;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/24 23:24
 */
public interface TBService {
    /**
     *
     * @param platform
     * @param cat
     * @param q
     * @param pageSize
     * @param pageNo
     * @return
     */
    List<TbkDgItemCouponGetResponse.TbkCoupon> getCoupon(Long platform, String cat, String q, Long pageSize, Long pageNo);
}
