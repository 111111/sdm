package com.sdm.service.impl;

import com.sdm.constant.SysConfigConstant;
import com.sdm.service.RedisService;
import com.sdm.service.TBService;
import com.sdm.tbapi.TbkAPI;
import com.taobao.api.domain.NTbkItem;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * @param req
     * @return
     */
    @Override
    public List<NTbkItem>  getTbkItem(TbkItemGetRequest req){
        try{
            return TbkAPI.getTbkItem(req);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param req
     * @return
     */
    @Override
    public List<TbkDgMaterialOptionalResponse.MapData> dgMaterialOptional(TbkDgMaterialOptionalRequest req){
        try{
            return TbkAPI.dgMaterialOptional(req);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
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
    public List<TbkDgItemCouponGetResponse.TbkCoupon> getCouponList(Long platform, String cat, String q, Long pageSize, Long pageNo){

        String key = "tbkCouponList." + platform + "." + cat + "." +  q + "." + pageSize + "_" + pageNo;

        List<TbkDgItemCouponGetResponse.TbkCoupon> tbkCouponList = redisService.getForList(key, TbkDgItemCouponGetResponse.TbkCoupon.class);
        if(tbkCouponList == null){
            try{
                tbkCouponList = TbkAPI.getCoupon(platform, cat, q, pageSize, pageNo);
                if(tbkCouponList != null){
                    redisService.set(key, tbkCouponList, SysConfigConstant.CACHE_TIMEOUT);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }


        return tbkCouponList;
    }


    /**
     *
     * @param num_iids 商品ID串，用,分割，最大40个
     * @param platform 链接形式：1：PC，2：无线，默认：１
     * @param ip ip地址，影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
     * @return
     */
    @Override
    public List<TbkItemInfoGetResponse.NTbkItem> getItemInfo(String num_iids, Long platform, String ip){
        String key = "getItemInfo." + num_iids + "." + platform;
        List<TbkItemInfoGetResponse.NTbkItem> tbkItemList = redisService.getForList(key, TbkItemInfoGetResponse.NTbkItem.class);
        if(tbkItemList == null){
            try{
                tbkItemList = TbkAPI.getTbkItemInfo(num_iids, platform, ip);
                if(tbkItemList != null){
                    redisService.set(key, tbkItemList, SysConfigConstant.CACHE_TIMEOUT);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return tbkItemList;
    }

    /**
     * 阿里妈妈推广券信息查询
     * @param me 带券ID与商品ID的加密串
     * @param itemId 商品ID
     * @param activityId 券ID
     * @return
     */
    @Override
    public TbkCouponGetResponse.MapData getCouponInfo(String me, Long itemId, String activityId){
        try{
            return TbkAPI.getCouponInfo(me, itemId, activityId);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 淘抢购api
     * @param startTime 最早开团时间 2016-08-09 09:00:00
     * @param endTime 最晚开团时间 2016-08-09 16:00:00
     * @param pageSize 页大小，默认40，1~40
     * @param pageNo 第几页，默认1，1~100
     * @return
     */
    @Override
    public List<TbkJuTqgGetResponse.Results> getTQG(Date startTime, Date endTime, Long pageSize, Long pageNo){
        try{
            return TbkAPI.getTQG(startTime, endTime, pageSize, pageNo);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {

        }
        return null;
    }

    /**
     * 淘宝客淘口令
     * @param userId 生成口令的淘宝用户ID
     * @param text true 口令弹框内容
     * @param url true 口令跳转目标页 https://uland.taobao.com/
     * @param logo 口令弹框logoURL
     * @param ext 扩展字段JSON格式 {}
     * @return
     */
    @Override
    public TbkTpwdCreateResponse.MapData createTpwd(String userId, String text, String url, String logo, String ext){
        try{
            return TbkAPI.createTpwd(userId, text, url, logo, ext);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public String createTpwdWireless(Long userId, String text, String url, String logo, String ext){
        try{
            return TbkAPI.createtpwdwireless(userId, text, url, logo, ext);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param req
     * @return
     */
    @Override
    public List<TbkJuTqgGetResponse.Results> getTQG(TbkJuTqgGetRequest req){
        try{
            return TbkAPI.getTQG(req);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }

    /**
     * 淘宝客物料下行-导购
     * 通用物料推荐，传入官方公布的物料id，可获取指定物料
     * @param mid 官方的物料Id(详细物料id见：https://tbk.bbs.taobao.com/detail.html?appId=45301&postId=8576096)
     * @param pageSize 页大小，默认20，1~100
     * @param pageNo 第几页，默认：1
     * @return
     */
    public List<TbkDgOptimusMaterialResponse.MapData> optimusMaterial(Long mid, Long pageSize, Long pageNo){
        try{
            return TbkAPI.optimusMaterial(mid, pageSize, pageNo);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
