package com.sdm.tbapi;

import com.sdm.cache.SysConfigCache;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * com.sdm.tbapi说明:
 * Created by qinyun
 * 2018/6/22 15:15
 */
public class TbkAPI {
    private static Logger logger = LoggerFactory.getLogger(TbkAPI.class);

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

        TaobaoClient client = new DefaultTaobaoClient(SysConfigCache.getTBApiUrl(), SysConfigCache.getTBAppkey(), SysConfigCache.getTBSecret());
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

        TbkDgItemCouponGetResponse rsp = client.execute(req);
        logger.info(rsp.getBody());

        return rsp.getResults();
    }
}
