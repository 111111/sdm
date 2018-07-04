package com.sdm.util;

/**
 * com.sdm.util说明:
 * Created by qinyun
 * 2018/6/28 10:32
 */
public class TBUtils {

    /**
     *
     * @param couponname
     * @return
     */
    public static Integer couponname2fullAmount(String couponname){
        int fullAmount = 0;
        if(couponname.startsWith("满")){
            String fullAmountStr = couponname.substring(couponname.indexOf("满") + 1, couponname.indexOf("元"));
            fullAmount = StringUtils.nullToInteger(fullAmountStr, 0);
        }
        return fullAmount;
    }

    /**
     *
     * @param couponname
     * @return
     */
    public static Integer couponname2preferentialAmount(String couponname){
        int preferentialAmount = 0;
        if(couponname.startsWith("满")){
            //满10元减5元
            String preferentialAmountStr = couponname.substring(couponname.indexOf("减") + 1, couponname.lastIndexOf("元"));
            preferentialAmount = StringUtils.nullToInteger(preferentialAmountStr, 0);

        }else{
            //3元无条件券
            String preferentialAmountStr = couponname.substring(0, couponname.lastIndexOf("元"));
            preferentialAmount = StringUtils.nullToInteger(preferentialAmountStr, 0);
        }
        return preferentialAmount;
    }
}
