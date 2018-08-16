package com.sdm.tbapi;

import com.sdm.cache.SysConfigCache;
import com.sdm.util.ExcelUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.NTbkItem;
import com.taobao.api.domain.NTbkShop;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * com.sdm.tbapi说明:
 * Created by qinyun
 * 2018/6/22 15:15
 */
public class TbkAPI {
    private static Logger logger = LoggerFactory.getLogger(TbkAPI.class);

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    public static List<TbkDgItemCouponGetResponse.TbkCoupon> getCoupon(TbkDgItemCouponGetRequest req )throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkDgItemCouponGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());

        return rsp.getResults();
    }
    /**
     * 好券清单API【导购】
     * http://open.taobao.com/api.htm?docId=29821&docType=2
     * @param platform 1：PC，2：无线，默认：1
     * @param cat 后台类目ID，用,分割，最大10个，该ID可以通过taobao.itemcats.get接口获取到
     * @param q 查询词
     * @param pageSize 页大小，默认20，1~100
     * @param pageNo 第几页，默认：1（当后台类目和查询词均不指定的时候，最多出10000个结果，即page_no*page_size不能超过10000；当指定类目或关键词的时候，则最多出100个结果）
     * @return
     * @throws Exception
     */
    public static List<TbkDgItemCouponGetResponse.TbkCoupon> getCoupon(Long platform, String cat, String q, Long pageSize, Long pageNo)throws Exception{

        TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
        req.setAdzoneId(SysConfigCache.getTBAdzoneId());
        if(platform != null && platform > 0){
            req.setPlatform(platform);
        }
        if(cat != null && !"".equals(cat)){
            req.setCat(cat);
        }
        if(pageSize != null && pageSize > 0){
            req.setPageSize(pageSize);
        }
        if(q != null && !"".equals(q)){
            req.setQ(q);
        }
        if(pageNo != null && pageNo > 0){
            req.setPageNo(pageNo);
        }

        return getCoupon(req);
    }

    public static TbkCouponGetResponse.MapData getCouponInfo(TbkCouponGetRequest req)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkCouponGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return  rsp.getData();
    }
    /**
     * 阿里妈妈推广券信息查询
     * @param me 带券ID与商品ID的加密串
     * @param itemId 商品ID
     * @param activityId 券ID
     * @return
     */
    public static TbkCouponGetResponse.MapData getCouponInfo(String me, Long itemId, String activityId)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkCouponGetRequest req = new TbkCouponGetRequest();
        if(me != null && !"".equals(me)){
            req.setMe(me);
        }
        if(itemId != null && itemId > 0){
            req.setItemId(itemId);
        }

        if(activityId != null && !"".equals(activityId)){
            req.setActivityId(activityId);
        }

        TbkCouponGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return  rsp.getData();
    }

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    public static List<NTbkItem>  getTbkItem(TbkItemGetRequest req)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkItemGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResults();
    }
    /**
     * 淘宝客商品查询
     * @param q 查询词
     * @param cat 后台类目ID，用,分割，最大10个，该ID可以通过taobao.itemcats.get接口获取到
     * @param itemloc 所在地
     * @param sort  tk_rate_des 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi）
     * @param isTmall 是否商城商品，设置为true表示该商品是属于淘宝商城商品，设置为false或不设置表示不判断这个属性
     * @param isOverseas 是否海外商品，设置为true表示该商品是属于海外商品，设置为false或不设置表示不判断这个属性
     * @param startPrice 折扣价范围下限，单位：元
     * @param endPrice 折扣价范围上限，单位：元
     * @param startTkRate 淘客佣金比率上限，如：1234表示12.34%
     * @param endTkRate 淘客佣金比率下限，如：1234表示12.34%
     * @param platform 链接形式：1：PC，2：无线，默认：１
     * @param pageSize 第几页，默认：１
     * @param pageNo 页大小，默认20，1~100
     * @return
     * @throws Exception
     */
    public static List<NTbkItem>  getTbkItem(String q, String cat, String itemloc, String sort, Boolean isTmall, Boolean isOverseas,
                                             Long startPrice, Long endPrice, Long startTkRate, Long endTkRate, Long platform, Long pageSize, Long pageNo)throws Exception{
        TbkItemGetRequest req = new TbkItemGetRequest();
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
        if(q != null && !"".equals(q)){
            req.setQ(q);
        }
        if(cat != null && !"".equals(cat)){
            req.setCat(cat);
        }
        if(itemloc != null && !"".equals(itemloc)){
            req.setItemloc(itemloc);
        }
        if(sort != null && !"".equals(sort)){
            req.setSort(sort);
        }

        req.setIsTmall(isTmall);
        req.setIsOverseas(isOverseas);

        if(startPrice != null && startPrice >= 0){
            req.setStartPrice(startPrice);
        }
        if(endPrice != null && endPrice >= 0){
            req.setEndPrice(endPrice);
        }
        if(startTkRate != null && startTkRate >= 0){
            req.setStartTkRate(startTkRate);
        }
        if(endTkRate != null && endTkRate >= 0){
            req.setEndTkRate(endTkRate);
        }

        if(platform != null && platform > 0){
            req.setPlatform(platform);
        }
        if(pageSize != null && pageSize > 0){
            req.setPageSize(pageSize);
        }
        if(pageNo != null && pageNo > 0){
            req.setPageNo(pageNo);
        }
        return getTbkItem(req);
    }


    /**
     * taobao.tbk.dg.material.optional( 通用物料搜索API（导购） )
     * @param startDsr 店铺dsr评分，筛选高于等于当前设置的店铺dsr评分的商品0-50000之间
     * @param platform 链接形式：1：PC，2：无线，默认：１
     * @param startTkRate 淘客佣金比率下限，如：1234表示12.34%
     * @param endTkRate 淘客佣金比率上限，如：1234表示12.34%
     * @param startPrice 折扣价范围上限，单位：元
     * @param endPrice 折扣价范围下限，单位：元
     * @param isOverseas 是否海外商品，设置为true表示该商品是属于海外商品，设置为false或不设置表示不判断这个属性
     * @param isTmall 是否商城商品，设置为true表示该商品是属于淘宝商城商品，设置为false或不设置表示不判断这个属性
     * @param sort tk_rate_des	排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price）
     * @param itemloc 所在地
     * @param cat 后台类目ID，用,分割，最大10个，该ID可以通过taobao.itemcats.get接口获取到
     * @param q 查询词
     * @param hasCoupon 是否有优惠券，设置为true表示该商品有优惠券，设置为false或不设置表示不判断这个属性
     * @param ip ip参数影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
     * @param adzoneId true mm_xxx_xxx_xxx的第三位
     * @param needFreeShipment 是否包邮，true表示包邮，空或false表示不限
     * @param needPrepay 是否加入消费者保障，true表示加入，空或false表示不限
     * @param includePayRate30 成交转化是否高于行业均值
     * @param includeGoodRate 好评率是否高于行业均值
     * @param includeRfdRate 退款率是否低于行业均值
     * @param npxLevel 牛皮癣程度，取值：1:不限，2:无，3:轻微
     * @param pageNo 第几页，默认：１
     * @param pageSize 页大小，默认20，1~100
     * @return
     * @throws Exception
     */
    public List<TbkDgMaterialOptionalResponse.MapData> dgMaterialOptional(Long startDsr, Long platform, Long startTkRate, Long endTkRate,
                                                                          Long startPrice, Long endPrice, Boolean isOverseas, Boolean isTmall,
                                                                          String sort, String itemloc, String cat, String q, Boolean hasCoupon, String ip,
                                                                          Long adzoneId, Boolean needFreeShipment, Boolean needPrepay, Boolean includePayRate30,
                                                                          Boolean includeGoodRate, Boolean includeRfdRate, Long npxLevel, Long pageNo, Long pageSize)throws Exception {
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setStartDsr(startDsr);
        req.setPlatform(platform);
        req.setStartTkRate(startTkRate);
        req.setEndTkRate(endTkRate);
        req.setStartPrice(startPrice);
        req.setEndPrice(endPrice);
        req.setIsOverseas(isOverseas);
        req.setIsTmall(isTmall);
        req.setSort(sort);
        req.setItemloc(itemloc);
        req.setCat(cat);
        req.setQ(q);
        req.setHasCoupon(hasCoupon);
        req.setIp(ip);
        req.setAdzoneId(adzoneId);
        req.setNeedFreeShipment(needFreeShipment);
        req.setNeedPrepay(needPrepay);
        req.setIncludePayRate30(includePayRate30);
        req.setIncludeGoodRate(includeGoodRate);
        req.setIncludeRfdRate(includeRfdRate);
        req.setNpxLevel(npxLevel);
        req.setPageSize(pageSize);
        req.setPageNo(pageNo);
        return dgMaterialOptional(req);
    }
    public static List<TbkDgMaterialOptionalResponse.MapData> dgMaterialOptional(TbkDgMaterialOptionalRequest req)throws Exception{

        TaobaoClient client = getTaobaoClient();

        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResultList();
    }

    public static List<TbkItemInfoGetResponse.NTbkItem> getTbkItemInfo(TbkItemInfoGetRequest req)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkItemInfoGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResults();
    }
    /**
     *淘宝客商品详情（简版）
     * @param num_iids 商品ID串，用,分割，最大40个
     * @param platform 链接形式：1：PC，2：无线，默认：１
     * @param ip ip地址，影响邮费获取，如果不传或者传入不准确，邮费无法精准提供
     * @return
     * @throws Exception
     */
    public static List<TbkItemInfoGetResponse.NTbkItem> getTbkItemInfo(String num_iids, Long platform, String ip)throws Exception{
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();

        req.setNumIids(num_iids);
        if(platform != null && platform > 0){
            req.setPlatform(platform);
        }
        if(ip != null && !"".equals(ip)){
            req.setIp(ip);
        }

        return getTbkItemInfo(req);
    }
    public static List<NTbkShop> getShopRecommend(TbkShopRecommendGetRequest req)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkShopRecommendGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResults();
    }
    /**
     * 淘宝客店铺关联推荐查
     * @param userId 卖家Id
     * @param platform 链接形式：1：PC，2：无线，默认：１
     * @return
     */
    public static List<NTbkShop> getShopRecommend(Long userId, Long platform)throws Exception{
        TbkShopRecommendGetRequest req = new TbkShopRecommendGetRequest();
        //需返回的字段列表
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
        req.setUserId(userId);
        //返回数量，默认20，最大值40
        req.setCount(20L);
        req.setPlatform(platform);
        return getShopRecommend(req);

    }
    public static List<TbkJuTqgGetResponse.Results> getTQG(TbkJuTqgGetRequest req) throws Exception {
        TaobaoClient client = getTaobaoClient();
        TbkJuTqgGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResults();
    }
    /**
     * 淘抢购api
     * @param startTime 最早开团时间 2016-08-09 09:00:00
     * @param endTime 最晚开团时间 2016-08-09 16:00:00
     * @param pageSize 页大小，默认40，1~40
     * @param pageNo 第几页，默认1，1~100
     * @return
     */
    public static List<TbkJuTqgGetResponse.Results> getTQG(Date startTime, Date endTime, Long pageSize, Long pageNo) throws Exception {
        TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
        req.setAdzoneId(SysConfigCache.getTBAdzoneId());
        req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
        req.setStartTime(startTime);
        req.setEndTime(endTime);
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);
        return getTQG(req);
    }


    public static TbkTpwdCreateResponse.MapData createTpwd(TbkTpwdCreateRequest req)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkTpwdCreateResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return  rsp.getData();
    }
    /**
     * 淘宝客淘口令
     * @param userId 生成口令的淘宝用户ID  57762989
     * @param text true 口令弹框内容
     * @param url true 口令跳转目标页 https://uland.taobao.com/
     * @param logo 口令弹框logoURL
     * @param ext 扩展字段JSON格式 {}
     * @return
     */
    public static TbkTpwdCreateResponse.MapData createTpwd(String userId, String text, String url, String logo, String ext)throws Exception{
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        if(userId != null && !"".equals(userId)){
            req.setUserId(userId);
        }

        req.setText(text);
        req.setUrl(url);
        if(logo != null && !"".equals(logo)){
            req.setLogo(logo);
        }
        if(ext != null && !"".equals(ext)){
            req.setExt(ext);
        }

        return  createTpwd(req);
    }

    /**
     * 淘宝客物料下行-导购
     *通用物料推荐，传入官方公布的物料id，可获取指定物料
     * @param mid 官方的物料Id(详细物料id见：https://tbk.bbs.taobao.com/detail.html?appId=45301&postId=8576096)
     * @param pageSize 页大小，默认20，1~100
     * @param pageNo 第几页，默认：1
     * @return
     */
    public static List<TbkDgOptimusMaterialResponse.MapData> optimusMaterial(Long mid, Long pageSize, Long pageNo) throws Exception{
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setAdzoneId(SysConfigCache.getTBAdzoneId());
        req.setPageSize(pageSize);
        req.setPageNo(pageNo);
        req.setMaterialId(mid);
        return optimusMaterial(req);
    }
    public static List<TbkDgOptimusMaterialResponse.MapData> optimusMaterial(TbkDgOptimusMaterialRequest req) throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResultList();
    }


    public static String  createtpwdwireless(Long userId, String text, String url, String logo, String ext)throws Exception{
        TaobaoClient client = getTaobaoClient();
        WirelessShareTpwdCreateRequest req = new WirelessShareTpwdCreateRequest();
        WirelessShareTpwdCreateRequest.GenPwdIsvParamDto obj1 = new WirelessShareTpwdCreateRequest.GenPwdIsvParamDto();
        obj1.setExt(ext);
        obj1.setLogo(logo);
        obj1.setUrl(url);
        obj1.setText(text);
        obj1.setUserId(userId);
        req.setTpwdParam(obj1);
        WirelessShareTpwdCreateResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getModel();
    }

    /**
     * taobao.tbk.item.recommend.get( 淘宝客商品关联推荐查询 )
     * @param numiid 商品Id
     * @param count 返回数量，默认20，最大值40
     * @param platform 链接形式：1：PC，2：无线，默认：１
     * @return
     * @throws Exception
     */
    public static  List<NTbkItem> itemRecommend(Long numiid, Long count, Long platform)throws Exception{
        TaobaoClient client = getTaobaoClient();
        TbkItemRecommendGetRequest req = new TbkItemRecommendGetRequest();
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,nick,seller_id,volume");
        req.setNumIid(numiid);
        req.setCount(count);
        req.setPlatform(platform);
        TbkItemRecommendGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());
        return rsp.getResults();
    }


    /**
     *
     * @return
     */
    private static TaobaoClient getTaobaoClient(){
        return new DefaultTaobaoClient(SysConfigCache.getTBApiUrl(), SysConfigCache.getTBAppkey(), SysConfigCache.getTBSecret());
    }
}
