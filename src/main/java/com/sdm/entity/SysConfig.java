package com.sdm.entity;

import java.io.Serializable;
import java.util.Date;

public class SysConfig implements Serializable {
    private Integer id;

    private String paramname;

    private String paramvalue;

    private String paramdesc;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamname() {
        return paramname;
    }

    public void setParamname(String paramname) {
        this.paramname = paramname == null ? null : paramname.trim();
    }

    public String getParamvalue() {
        return paramvalue;
    }

    public void setParamvalue(String paramvalue) {
        this.paramvalue = paramvalue == null ? null : paramvalue.trim();
    }

    public String getParamdesc() {
        return paramdesc;
    }

    public void setParamdesc(String paramdesc) {
        this.paramdesc = paramdesc == null ? null : paramdesc.trim();
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
}