package com.sdm.controller;

import com.github.pagehelper.PageInfo;
import com.sdm.bean.TBCouponBean;
import com.sdm.cache.SysConfigCache;
import com.sdm.entity.CategoryRecommend;
import com.sdm.entity.TbCategory;
import com.sdm.service.CategoryService;
import com.sdm.service.TBService;
import com.sdm.util.IpUtil;
import com.sdm.util.TBUtils;
import com.taobao.api.domain.NTbkItem;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * com.sdm.controller说明:
 * Created by qinyun
 * 2018/7/3 17:23
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private TBService tbService;

    @Autowired
    private CategoryService categoryService;

    /**
     *
     * @param pi
     * @param platform
     * @param searchName
     * @return
     */
    @RequestMapping("/tbcouponlist")
    @ResponseBody
    public Object tbCcuponListJson(Long pi, Long platform, String searchName, String cat ){
        if(platform == null || platform <= 0){
            platform = 1L;
        }

        if(pi == null || pi <= 0){
            pi = 1L;
        }
        if("null".equals(searchName)){
            searchName = null;
        }
        List<TbkDgItemCouponGetResponse.TbkCoupon> tbkCouponList = tbService.getCouponList(platform, cat, searchName, 20L, pi);
        List<TBCouponBean> tbCouponBeanList = new ArrayList<>();
        if(tbkCouponList != null && tbkCouponList.size() > 0){
            for(TbkDgItemCouponGetResponse.TbkCoupon coupon : tbkCouponList){
                String couponname = coupon.getCouponInfo();
                BigDecimal price = new BigDecimal(coupon.getZkFinalPrice());
//                int fullAmount = TBUtils.couponname2fullAmount(couponname);
                int preferentialAmount = TBUtils.couponname2preferentialAmount(couponname);


                TBCouponBean tbCouponBean = new TBCouponBean();
                BeanUtils.copyProperties(coupon, tbCouponBean);
                tbCouponBean.setCommissionPrice(price.subtract(new BigDecimal(preferentialAmount)));
                tbCouponBeanList.add(tbCouponBean);

            }
        }


        return tbCouponBeanList;
    }

    @RequestMapping("/goodsinfo")
    @ResponseBody
    public Object goodsInfoJson(HttpServletRequest request, String goodsid, Long platform){

        Map reMap = new HashMap<>();
        if(goodsid == null || "".equals(goodsid)){
            return null;
        }
        if(platform == null || platform <= 0){
            platform = 1L;
        }
        String ip = IpUtil.getIpAddr(request);
        List<TbkItemInfoGetResponse.NTbkItem> tbkItemList = tbService.getItemInfo(goodsid, platform, ip);
        TbkItemInfoGetResponse.NTbkItem tbkItem = null;

        if(tbkItemList != null){
            tbkItem = tbkItemList.get(0);
        }


        reMap.put("item", tbkItem);

        TbkDgItemCouponGetResponse.TbkCoupon coupon = null;
        if(tbkItem != null){
            List<TbkDgItemCouponGetResponse.TbkCoupon> tbkCouponList = tbService.getCouponList(platform, null, tbkItem.getTitle(), 10L, 1L);
            if(tbkCouponList != null){
                for(TbkDgItemCouponGetResponse.TbkCoupon temp : tbkCouponList){
                    if(temp.getNumIid().equals(tbkItem.getNumIid())){
                        coupon = temp;

                        break;
                    }
                }
            }
        }
        reMap.put("coupon", coupon);

        if(coupon != null){

            BigDecimal price = new BigDecimal(coupon.getZkFinalPrice());
//                int fullAmount = TBUtils.couponname2fullAmount(couponname);
            int preferentialAmount = TBUtils.couponname2preferentialAmount(coupon.getCouponInfo());


            TBCouponBean tbCouponBean = new TBCouponBean();
            BeanUtils.copyProperties(coupon, tbCouponBean);
            tbCouponBean.setCommissionPrice(price.subtract(new BigDecimal(preferentialAmount)));

            reMap.put("coupon", tbCouponBean);

        }
        return reMap;

    }
    @RequestMapping("/gettbkitemlist")
    @ResponseBody
    public Object getTbkItemList(String q, String cat, String itemloc, String sort, Boolean isTmall, Boolean isOverseas,
                                 Long startPrice, Long endPrice, Long startTkRate, Long endTkRate, Long platform, Long pageNo){
        TbkItemGetRequest req = new TbkItemGetRequest();
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
        if(q != null && !"".equals(q) && !"null".equals(q)){
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
        req.setPageSize(20L);
        if(pageNo != null && pageNo > 0){
            req.setPageNo(pageNo);
        }
        List<NTbkItem> tbkItemList = tbService.getTbkItem(req);
        return tbkItemList;
    }

    /**
     * 参数q与cat不能都为空
     * @param startDsr
     * @param platform
     * @param startTkRate
     * @param endTkRate
     * @param startPrice
     * @param endPrice
     * @param isOverseas
     * @param isTmall
     * @param sort
     * @param itemloc
     * @param cat
     * @param q
     * @param hasCoupon
     * @param ip
     * @param needFreeShipment
     * @param needPrepay
     * @param includePayRate30
     * @param includeGoodRate
     * @param includeRfdRate
     * @param npxLevel
     * @param pageNo
     * @return
     */
    @RequestMapping("/dgmaterialoptional")
    @ResponseBody
    public Object dgMaterialOptional(Long startDsr, Long platform, Long startTkRate, Long endTkRate,
                                     Long startPrice, Long endPrice, Boolean isOverseas, Boolean isTmall,
                                     String sort, String itemloc, String cat, String q, Boolean hasCoupon, String ip,
                                     Boolean needFreeShipment, Boolean needPrepay, Boolean includePayRate30,
                                     Boolean includeGoodRate, Boolean includeRfdRate, Long npxLevel, Long pageNo){
        if(platform == null || platform <= 0){
            platform = 1L;
        }

        if(pageNo == null || pageNo <= 0){
            pageNo = 1L;
        }
        if("null".equals(q)){
            q = null;
        }
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
        req.setAdzoneId(SysConfigCache.getTBAdzoneId());
        req.setNeedFreeShipment(needFreeShipment);
        req.setNeedPrepay(needPrepay);
        req.setIncludePayRate30(includePayRate30);
        req.setIncludeGoodRate(includeGoodRate);
        req.setIncludeRfdRate(includeRfdRate);
        req.setNpxLevel(npxLevel);
        req.setPageSize(20L);
        req.setPageNo(pageNo);
        List<TbkDgMaterialOptionalResponse.MapData> mapDataList = tbService.dgMaterialOptional(req);
        return mapDataList;

    }

    /**
     * 淘宝客淘口令
     * @param text true 口令弹框内容
     * @param url true 口令跳转目标页 https://uland.taobao.com/
     * @param logo 口令弹框logoURL
     * @param ext 扩展字段JSON格式 {}
     * @return
     */
    @RequestMapping("/createtbwd")
    @ResponseBody
    public Object createTpwd(String text, String url, String logo, String ext){

        TbkTpwdCreateResponse.MapData data = tbService.createTpwd(SysConfigCache.getTBUserid(), text, url, logo, ext);
        return data;
    }
    @RequestMapping("/createtpwdwireless")
    @ResponseBody
    public Object createTpwdWireless(String text, String url, String logo, String ext){

        String t = tbService.createTpwdWireless(Long.parseLong(SysConfigCache.getTBUserid()), text, url, logo, ext);
        return t;
    }
    @RequestMapping("/gettqg")
    @ResponseBody
    public Object getTQG(Date startTime, Date endTime, Long pageNo){
        TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
        req.setAdzoneId(SysConfigCache.getTBAdzoneId());
        req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
        req.setStartTime(startTime);
        req.setEndTime(endTime);
        req.setPageNo(pageNo);
        req.setPageSize(20L);
        List<TbkJuTqgGetResponse.Results> results = tbService.getTQG(req);
        return results;

    }

    @RequestMapping("/getcategoryrecommend")
    @ResponseBody
    public Object getCateGoryRecommend(){
        List<CategoryRecommend> categoryRecommendList = categoryService.selectListAll();
        return categoryRecommendList;

    }

    @RequestMapping("/category")
    @ResponseBody
    public Object getCateGory(String q, Long tbpid, Integer rid, Integer ps, Integer pi){
        if(tbpid == null || tbpid < 0){
            tbpid = 0L;
        }
        if(ps == null || ps <= 0){
            ps = 20;
        }
        if(pi == null || pi <= 0){
            pi = 1;
        }
        if("null".equals(q)){
            q = null;
        }
        CategoryRecommend categoryRecommend = null;
        if(rid != null && rid > 0){
            categoryRecommend = categoryService.selectCategoryRecommendById(rid);
        }
        TbCategory tbCategory = null;
        if(tbpid != null && tbpid > 0 ){
            tbCategory = categoryService.findTbCategoryByTbcid(tbpid);
        }
        PageInfo<TbCategory> pageInfo = categoryService.findTBCategoryPage(q, tbpid, rid, pi, ps);

        Map reMap = new HashMap();
        reMap.put("recommend", categoryRecommend);
        reMap.put("category", tbCategory);
        reMap.put("categoryList", pageInfo.getList());
        return reMap;
    }

    @RequestMapping("/optimusmaterial")
    @ResponseBody
    public Object optimusMaterial(Long mid, Long pi){
        if(pi == null || pi <= 0){
            pi = 1L;
        }
        return tbService.optimusMaterial(mid, 20L, pi);

    }

}
