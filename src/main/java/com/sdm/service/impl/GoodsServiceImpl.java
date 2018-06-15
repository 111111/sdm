package com.sdm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sdm.dao.CategoryMapper;
import com.sdm.dao.CouponMapper;
import com.sdm.dao.GoodsMapper;
import com.sdm.entity.Category;
import com.sdm.entity.Coupon;
import com.sdm.entity.Goods;
import com.sdm.service.GoodsService;
import com.sdm.util.DateUtil;
import com.sdm.util.ExcelUtil;
import com.sdm.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 18/5/28 16:03
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *
     * @param pi
     * @param ps
     * @return
     */
    @Override
    public List<Goods> getGoodPage(int pi, int ps){
        Page<Goods> page = PageHelper.startPage(pi, ps, true);
        Goods paramGoods = new Goods();
        List<Goods> goodsList = goodsMapper.selectList(paramGoods);
        return goodsList;
    }

    @Override
    public List<Goods> getGoodPage(String goodsName, Integer categoryid, int pi, int ps){
        Page<Goods> page = PageHelper.startPage(pi, ps, true);
        Goods paramGoods = new Goods();

        if(goodsName != null && !"".equals(goodsName.trim())){
            paramGoods.setGoodname(goodsName.trim());
        }

        if(categoryid != null && categoryid > 0){
            paramGoods.setCategoryIds(categoryid + "");
        }
        List<Goods> goodsList = goodsMapper.selectList(paramGoods);
        return goodsList;
    }


    @Override
    @Async
    public void importExcel(String filePath, String fileName){
        File file = new File(filePath + fileName);
        importExcel(file);
    }
    @Override
    @Async
    public void importExcel(File excelFile){

        List<List<Object>>  objList = null;
        try{
            objList = ExcelUtil.readExcel(excelFile);
        }catch(Exception ex){
            ex.printStackTrace();
            logger.error("importExcel error:", ex.getMessage());
        }
        if(objList != null && objList.size() > 0){
            for(int i = 1;i < objList.size(); i++){
                List<Object> rowList = objList.get(i);
                System.out.println("rowList"+i+" = " + rowList.size());
                Goods goods = row2Goods(rowList);
                Coupon coupon = row2Coupon(rowList);

                String c = goods.getCategory();
                String[] categoryNames = c.split("/");

                saveCategory(categoryNames);

                Goods goods_temp = goodsMapper.selectByTbId(goods.getTbid());
                if(goods_temp == null){
                    goods.setCreatetime(new Date());
                    goods.setUpdatetime(new Date());
                    goodsMapper.insert(goods);
                }else{
                    goods.setId(goods_temp.getId());
                    goods.setUpdatetime(new Date());
                    goodsMapper.updateByPrimaryKeySelective(goods);
                }

                System.out.println("goods = " + goods);
                Coupon coupon_temp = new Coupon();
                coupon_temp.setGoodsid(goods.getId());
                coupon_temp.setCouponid(coupon.getCouponid());
                coupon_temp = couponMapper.selectByGoodsidAndCouponId(coupon_temp);
                if(coupon_temp == null){
                    coupon.setGoodsid(goods.getId());
                    coupon.setCreatetime(new Date());
                    coupon.setUpdatetime(new Date());
                    couponMapper.insert(coupon);
                }else{
                    coupon.setId(coupon_temp.getId());
                    coupon.setGoodsid(goods.getId());
                    coupon.setUpdatetime(new Date());
                    couponMapper.updateByPrimaryKeySelective(coupon);
                }

            }
        }

    }

    /**
     *
     * @param rowList
     * @return
     */
    private Goods row2Goods(List<Object> rowList){
        String a1, b2, c3, d4, e5, f6, g7, h8, i9, j10, k11, l12, m13, n14, o15, p16, q17, r18, s19, t20, u21, v22;
        a1 = (String)rowList.get(0);
        b2 = (String)rowList.get(1);
        c3 = (String)rowList.get(2);
        d4 = (String)rowList.get(3);
        e5 = (String)rowList.get(4);
        f6 = (String)rowList.get(5);
        g7 = (String)rowList.get(6);
        h8 = (String)rowList.get(7);
        i9 = (String)rowList.get(8);
        j10 = (String)rowList.get(9);
        k11 = (String)rowList.get(10);
        l12 = (String)rowList.get(11);
        m13 = (String)rowList.get(12);
        n14 = (String)rowList.get(13);
        o15 = (String)rowList.get(14);
        p16 = (String)rowList.get(15);
        q17 = (String)rowList.get(16);
        r18 = (String)rowList.get(17);
        s19 = (String)rowList.get(18);
        t20 = (String)rowList.get(19);
        u21 = (String)rowList.get(20);
        v22 = (String)rowList.get(21);
//        logger.info("rowList:a1 = {},b2 = {},c3 = {},d4 = {},e5 = {},f6 = {},g7 = {},h8 = {},i9 = {},j10 = {},k11 = {},l12 = {},m13 = {},n14 = {},o15 = {},p16 = {},,q17 = {},r18 = {},s19 = {},t20 = {},u21 = {},v22 = {}"
//                , a1, b2, c3, d4, e5, f6, g7, h8, i9, j10,k11, l12, m13, n14, o15, p16, q17, r18, s19, t20, u21, v22);
        Goods goods = new Goods();
        goods.setTbid(a1);
        goods.setGoodname(b2);
        goods.setMainpicurl(c3);
        goods.setViewurl(d4);
        goods.setCategory(e5);
        goods.setTbkurl(f6);
        goods.setPrice(new BigDecimal(g7));
        goods.setMonthsales(Integer.parseInt(h8));
        goods.setIncomeratio(new BigDecimal(i9));
        goods.setCommision(new BigDecimal(j10));
        goods.setSellername(k11);
        goods.setSellerid(l12);
        goods.setShopname(m13);
        goods.setPlatform(n14);

        goods.setCouponid(o15);
        goods.setCoupontotal(Integer.parseInt(p16));
        goods.setCouponsurplus(Integer.parseInt(q17));
        goods.setCouponname(r18);
        goods.setCouponsstarttime(DateUtil.converDate(s19));
        goods.setCouponsendtime(DateUtil.converDate(t20));
        goods.setCouponsurl(u21);
        goods.setCouponsextensionurl(v22);
//        System.out.println("goods = " + goods);

        return goods;
    }

    /**
     *
     * @param rowList
     * @return
     */
    private Coupon row2Coupon(List<Object> rowList){
        Coupon coupon = new Coupon();

        String a1, b2, c3, d4, e5, f6, g7, h8, i9, j10, k11, l12, m13, n14, o15, p16, q17, r18, s19, t20, u21, v22;
        a1 = (String)rowList.get(0);
        o15 = (String)rowList.get(14);
        p16 = (String)rowList.get(15);
        q17 = (String)rowList.get(16);
        r18 = (String)rowList.get(17);
        s19 = (String)rowList.get(18);
        t20 = (String)rowList.get(19);
        u21 = (String)rowList.get(20);
        v22 = (String)rowList.get(21);



        coupon.setTbid(a1);
        coupon.setCouponid(o15);
        coupon.setCoupontotal(Integer.parseInt(p16));
        coupon.setCouponsurplus(Integer.parseInt(q17));
        coupon.setCouponname(r18);
        coupon.setCouponsstarttime(DateUtil.converDate(s19));
        coupon.setCouponsendtime(DateUtil.converDate(t20));
        coupon.setCouponsurl(u21);
        coupon.setCouponsextensionurl(v22);
        coupon.setCreatetime(new Date());

        int fullAmount = 0;
        int preferentialAmount = 0;
        if(r18.startsWith("满")){
            //满10元减5元
            String fullAmountStr = r18.substring(r18.indexOf("满") + 1, r18.indexOf("元"));
            fullAmount = StringUtils.nullToInteger(fullAmountStr, 0);
            String preferentialAmountStr = r18.substring(r18.indexOf("减") + 1, r18.lastIndexOf("元"));
            preferentialAmount = StringUtils.nullToInteger(preferentialAmountStr, 0);

        }else{
            //3元无条件券
            String preferentialAmountStr = r18.substring(0, r18.lastIndexOf("元"));
            preferentialAmount = StringUtils.nullToInteger(preferentialAmountStr, 0);
        }
        coupon.setFullAmount(fullAmount);
        coupon.setPreferentialAmount(preferentialAmount);

        return coupon;
    }

    /**
     *
     * @param categoryNames
     * @return
     */
    private void saveCategory(String[] categoryNames){

        for(String cName : categoryNames){
            saveCategory(0, cName);
        }


    }

    /**
     *
     * @param parentid
     * @param categoryName
     * @return
     */
    private Category saveCategory(Integer parentid, String categoryName){
        Category category = new Category();
        category.setParentid(parentid);
        category.setCategoryname(categoryName);
        category = categoryMapper.selectByParameter(category);
        if(category == null){
            category = new Category();
            category.setParentid(parentid);
            category.setCategoryname(categoryName);
            categoryMapper.insert(category);
        }
        return category;

    }
}
