package com.sdm.service.impl;

import com.sdm.service.RedisService;
import com.sdm.service.TBService;
import com.sdm.tbapi.TbkAPI;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 2018/6/24 23:24
 */
@Service
public class TBServiceImpl implements TBService {

    private Logger logger = LoggerFactory.getLogger(TBServiceImpl.class);

    @Autowired
    private RedisService redisService;


    /**
     *
     * @param platform
     * @param cat
     * @param q
     * @param pageSize
     * @param pageNo
     * @return
     */

    @Override
    public List<TbkDgItemCouponGetResponse.TbkCoupon> getCoupon(Long platform, String cat, String q, Long pageSize, Long pageNo){

        String key = "tbkCouponList." + platform + "." + cat + "." +  q + "." + pageSize + "_" + pageNo;

        List<TbkDgItemCouponGetResponse.TbkCoupon> tbkCouponList = redisService.getForList(key, TbkDgItemCouponGetResponse.TbkCoupon.class);
        if(tbkCouponList == null){
            try{
                tbkCouponList = TbkAPI.getCoupon(platform, cat, q, pageSize, pageNo);
                if(tbkCouponList != null){
                    redisService.set(key, tbkCouponList, 60 * 10);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }


        return tbkCouponList;
    }
}
