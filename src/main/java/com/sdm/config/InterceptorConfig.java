package com.sdm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * com.sdm.config说明:
 * Created by qinyun
 * 2018/6/13 14:50
 */
public class InterceptorConfig implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        log.info("---------------------开始进入请求地址拦截----------------------------");
        String uri = httpServletRequest.getRequestURI();

        StringBuffer param = new StringBuffer(32);

        Enumeration<String> pNames =  httpServletRequest.getParameterNames();
        for(Enumeration e = pNames; pNames.hasMoreElements();){
            String thisName = e.nextElement().toString();
            String thisValue = httpServletRequest.getParameter(thisName);
            param.append("&").append(thisName).append("=").append(thisValue);
        }

        log.info("request uri:{}?{}", uri, param);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        log.info("---------------视图渲染之后的操作-------------------------");
    }
}
