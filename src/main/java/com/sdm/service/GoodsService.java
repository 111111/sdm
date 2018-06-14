package com.sdm.service;

import com.github.pagehelper.PageInfo;
import com.sdm.entity.Goods;

import java.io.File;
import java.util.List;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 18/5/28 16:01
 */
public interface GoodsService {

    List<Goods> getGoodPage(int pi, int ps);

    List<Goods> getGoodPage(String goodsName, Integer categoryid, int pi, int ps);

    void importExcel(String filePath, String fileName);

    void importExcel(File excelFile);
}
