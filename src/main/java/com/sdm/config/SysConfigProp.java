package com.sdm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * com.sdm.config说明:
 * Created by qinyun
 * 18/5/29 22:41
 */
@Component
@ConfigurationProperties(prefix = "sdm.sys")
public class SysConfigProp {
    @Value("${sdm.sys.upload.path}")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
