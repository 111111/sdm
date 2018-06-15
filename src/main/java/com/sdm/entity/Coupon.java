package com.sdm.entity;

import java.util.Date;

public class Coupon {
    private Long id;

    private Long goodsid;

    private String tbid;

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

    private Integer fullAmount;

    private Integer preferentialAmount;

    private Goods goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public String getTbid() {
        return tbid;
    }

    public void setTbid(String tbid) {
        this.tbid = tbid == null ? null : tbid.trim();
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

    public Integer getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(Integer fullAmount) {
        this.fullAmount = fullAmount;
    }

    public Integer getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(Integer preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", goodsid=" + goodsid +
                ", tbid='" + tbid + '\'' +
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
                ", fullAmount=" + fullAmount +
                ", preferentialAmount=" + preferentialAmount +
                '}';
    }
}