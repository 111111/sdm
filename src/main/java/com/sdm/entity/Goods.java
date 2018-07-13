package com.sdm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Goods implements Serializable {
    private Long id;

    private String tbid;

    private String goodname;

    private String mainpicurl;

    private String viewurl;

    private String category;

    private String categoryIds;

    private String tbkurl;

    private BigDecimal price;

    private Integer monthsales;

    private BigDecimal incomeratio;

    private BigDecimal commision;

    private String sellername;

    private String sellerid;

    private String shopname;

    private String platform;

    private String couponid;

    private Integer coupontotal;

    private Integer couponsurplus;

    private String couponname;

    private Date couponsstarttime;

    private Date couponsendtime;

    private String couponsurl;

    private String couponsextensionurl;

    private Date createtime;

    private Date updatetime;

    private BigDecimal voucherPrice;

    private List<Coupon> couponList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTbid() {
        return tbid;
    }

    public void setTbid(String tbid) {
        this.tbid = tbid == null ? null : tbid.trim();
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname == null ? null : goodname.trim();
    }

    public String getMainpicurl() {
        return mainpicurl;
    }

    public void setMainpicurl(String mainpicurl) {
        this.mainpicurl = mainpicurl == null ? null : mainpicurl.trim();
    }

    public String getViewurl() {
        return viewurl;
    }

    public void setViewurl(String viewurl) {
        this.viewurl = viewurl == null ? null : viewurl.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getTbkurl() {
        return tbkurl;
    }

    public void setTbkurl(String tbkurl) {
        this.tbkurl = tbkurl == null ? null : tbkurl.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMonthsales() {
        return monthsales;
    }

    public void setMonthsales(Integer monthsales) {
        this.monthsales = monthsales;
    }

    public BigDecimal getIncomeratio() {
        return incomeratio;
    }

    public void setIncomeratio(BigDecimal incomeratio) {
        this.incomeratio = incomeratio;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid == null ? null : couponid.trim();
    }

    public Integer getCoupontotal() {
        return coupontotal;
    }

    public void setCoupontotal(Integer coupontotal) {
        this.coupontotal = coupontotal;
    }

    public Integer getCouponsurplus() {
        return couponsurplus;
    }

    public void setCouponsurplus(Integer couponsurplus) {
        this.couponsurplus = couponsurplus;
    }

    public String getCouponname() {
        return couponname;
    }

    public void setCouponname(String couponname) {
        this.couponname = couponname == null ? null : couponname.trim();
    }

    public Date getCouponsstarttime() {
        return couponsstarttime;
    }

    public void setCouponsstarttime(Date couponsstarttime) {
        this.couponsstarttime = couponsstarttime;
    }

    public Date getCouponsendtime() {
        return couponsendtime;
    }

    public void setCouponsendtime(Date couponsendtime) {
        this.couponsendtime = couponsendtime;
    }

    public String getCouponsurl() {
        return couponsurl;
    }

    public void setCouponsurl(String couponsurl) {
        this.couponsurl = couponsurl == null ? null : couponsurl.trim();
    }

    public String getCouponsextensionurl() {
        return couponsextensionurl;
    }

    public void setCouponsextensionurl(String couponsextensionurl) {
        this.couponsextensionurl = couponsextensionurl == null ? null : couponsextensionurl.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(BigDecimal voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public void addCoupon(Coupon coupon){
        if(couponList == null){
            couponList = new ArrayList<>();
        }
        couponList.add(coupon);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", tbid='" + tbid + '\'' +
                ", goodname='" + goodname + '\'' +
                ", mainpicurl='" + mainpicurl + '\'' +
                ", viewurl='" + viewurl + '\'' +
                ", category='" + category + '\'' +
                ", categoryIds='" + categoryIds + '\'' +
                ", tbkurl='" + tbkurl + '\'' +
                ", price=" + price +
                ", monthsales=" + monthsales +
                ", incomeratio=" + incomeratio +
                ", commision=" + commision +
                ", sellername='" + sellername + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", shopname='" + shopname + '\'' +
                ", platform='" + platform + '\'' +
                ", couponid='" + couponid + '\'' +
                ", coupontotal=" + coupontotal +
                ", couponsurplus=" + couponsurplus +
                ", couponname='" + couponname + '\'' +
                ", couponsstarttime=" + couponsstarttime +
                ", couponsendtime=" + couponsendtime +
                ", couponsurl='" + couponsurl + '\'' +
                ", couponsextensionurl='" + couponsextensionurl + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", voucherPrice=" + voucherPrice +
                ", couponList=" + couponList +
                '}';
    }
}