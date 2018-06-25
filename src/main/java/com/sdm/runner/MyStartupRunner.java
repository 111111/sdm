package com.sdm.runner;

import com.sdm.cache.SysConfigCache;
import com.sdm.service.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * com.sdm.runner说明:
 * Created by qinyun
 * 2018/6/22 15:08
 */
@Component
@Order(value = 1)
public class MyStartupRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public void run(String... args) throws Exception {
        SysConfigCache.putCache(sysConfigService.selectAll());
        logger.info("init sysConfig success.");
    }
}
