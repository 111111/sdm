package com.sdm.entity;

import java.io.Serializable;
import java.util.List;

public class TbCategory implements Serializable {
    private Integer id;

    private Long tbpid;

    private String cname;

    private Integer orderby;

    private Long tbcid;

    private Integer rid;

    private List<TbCategory> categoryList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTbpid() {
        return tbpid;
    }

    public void setTbpid(Long tbpid) {
        this.tbpid = tbpid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Long getTbcid() {
        return tbcid;
    }

    public void setTbcid(Long tbcid) {
        this.tbcid = tbcid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public List<TbCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<TbCategory> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "TbCategory{" +
                "id=" + id +
                ", tbpid=" + tbpid +
                ", cname='" + cname + '\'' +
                ", orderby=" + orderby +
                ", tbcid=" + tbcid +
                ", rid=" + rid +
                ", categoryList=" + categoryList +
                '}';
    }
}