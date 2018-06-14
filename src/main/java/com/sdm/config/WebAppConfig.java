package com.sdm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * com.sdm.config说明:
 * Created by qinyun
 * 2018/6/13 14:53
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器， url路径
        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**").excludePathPatterns("/js/*","/css/**","/bootstrap/**","/favicon**");
    }
}
