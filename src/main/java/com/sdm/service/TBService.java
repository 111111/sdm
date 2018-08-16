package com.sdm.service;

import com.taobao.api.domain.NTbkItem;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.response.*;

import java.util.Date;
import java.util.List;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/24 23:24
 */
public interface TBService {

    List<NTbkItem> getTbkItem(TbkItemGetRequest req);

    List<TbkDgMaterialOptionalResponse.MapData> dgMaterialOptional(TbkDgMaterialOptionalRequest req);

    List<TbkDgItemCouponGetResponse.TbkCoupon> getCouponList(Long platform, String cat, String q, Long pageSize, Long pageNo);

    List<TbkItemInfoGetResponse.NTbkItem> getItemInfo(String num_iids, Long platform, String ip);

    TbkCouponGetResponse.MapData getCouponInfo(String me, Long itemId, String activityId);

    List<TbkJuTqgGetResponse.Results> getTQG(Date startTime, Date endTime, Long pageSize, Long pageNo);

    TbkTpwdCreateResponse.MapData createTpwd(String userId, String text, String url, String logo, String ext);

    String createTpwdWireless(Long userId, String text, String url, String logo, String ext);

    List<TbkJuTqgGetResponse.Results> getTQG(TbkJuTqgGetRequest req);

    List<TbkDgOptimusMaterialResponse.MapData> optimusMaterial(Long mid, Long pageSize, Long pageNo);

    List<NTbkItem> itemRecommend(Long numiid, Long count, Long platform);
}
