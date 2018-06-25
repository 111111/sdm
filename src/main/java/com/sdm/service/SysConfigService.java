package com.sdm.service;

import com.sdm.entity.SysConfig;

import java.util.List;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/22 14:32
 */
public interface SysConfigService {

    List<SysConfig> selectAll();

    SysConfig selectByParamName(String paramname);
}
