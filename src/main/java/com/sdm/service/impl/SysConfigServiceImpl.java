package com.sdm.service.impl;

import com.sdm.dao.SysConfigMapper;
import com.sdm.entity.SysConfig;
import com.sdm.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.sdm.service.impl说明:
 * Created by qinyun
 * 2018/6/22 14:32
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     *
     * @return
     */
    @Override
    public List<SysConfig> selectAll(){
        return  sysConfigMapper.selectAll();
    }

    @Override
    public SysConfig selectByParamName(String paramname){
        return sysConfigMapper.selectByParamName(paramname);
    }


}
