package com.sdm.bean;

import java.io.Serializable;

/**
 * {"openId":"oqwB75cxrxskSLA7cFbJOzl7rRHc","nickName":"☁️云☁️","gender":1,"language":"zh_CN","city":"Shenzhen","province":"Guangdong","country":"China","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/dhBuZzkFztLL52wapdICb2ccoHGmbIb72KiaJl0jslTmGnlM73e9zPmf7Pr63Ay9qkG5ftc31WnzMKXPf2e6Psw/132","unionId":"oVAt_1rkQJLE5Oe1drMwwviurl7","watermark":{"timestamp":1531148543,"appid":"wx82630aab6a8a5ed5"}}
 * com.sdm.bean说明:
 * Created by qinyun
 * 2018/7/10 14:29
 * @author apple
 */
public class WxEncryptedDataBean implements Serializable {


    private String openId;
    private String nickName;
    private Integer gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;

//    private Watermark watermark;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }



    @Override
    public String toString() {
        return "WxEncryptedDataBean{" +
                "openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }
}
