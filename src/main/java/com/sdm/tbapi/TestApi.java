package com.sdm.tbapi;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkJuTqgGetResponse;

import java.util.List;

/**
 * com.sdm.tbapi说明:
 * Created by qinyun
 * 2018/6/21 23:01
 */
public class TestApi {

    private static String TB_API_URL="http://gw.api.taobao.com/router/rest";
    private static String TB_API_TBSANDBOX_URL="http://gw.api.tbsandbox.com/router/rest";

    private static String appkey = "24938428";
    private static String secret = "5f7f243dc798180280bfb52f8bf1c1e9";


    /**
     * mm_25345182_34304133_121558025
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        coupon();
//        item_info_get();
//        ju_tqg_get();
    }



    private static void itemget()throws  Exception{
        TaobaoClient client = new DefaultTaobaoClient(TB_API_URL, appkey, secret);
        TbkItemGetRequest req = new TbkItemGetRequest();
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
        req.setQ("女装");
        req.setCat("16,18");
//        req.setItemloc("杭州");
//        req.setSort("tk_rate_des");
//        req.setIsTmall(false);
//        req.setIsOverseas(false);
//        req.setStartPrice(10L);
//        req.setEndPrice(10L);
//        req.setStartTkRate(123L);
//        req.setEndTkRate(123L);
//        req.setPlatform(1L);
//        req.setPageNo(123L);
        req.setPageSize(20L);
        TbkItemGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    private static void coupon()throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(TB_API_URL, appkey, secret);
        TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
        req.setAdzoneId(912018751L);
//        req.setPlatform(1L);
//        req.setCat("16,18");
        req.setPageSize(20L);
//        req.setQ("女装");
        req.setPageNo(1L);
        TbkDgItemCouponGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        System.out.println("rsp.isSuccess() = " + rsp.isSuccess());
        System.out.println("rsp.getResults() = " + rsp.getResults());
    }

    private static void item_info_get()throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(TB_API_URL, appkey, secret);
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids("566992249578");
//        req.setPlatform(1L);
//        req.setIp("11.22.33.43");
        TbkItemInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }


    private static void ju_tqg_get()throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(TB_API_URL, appkey, secret);
        TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
        req.setAdzoneId(121558025L);
        req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
        req.setStartTime(StringUtils.parseDateTime("2018-06-22 09:00:00"));
        req.setEndTime(StringUtils.parseDateTime("2018-06-25 16:00:00"));
        req.setPageNo(1L);
        req.setPageSize(40L);
        TbkJuTqgGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }



}
