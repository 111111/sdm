package com.sdm.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * com.sdm.filter说明:
 * Created by qinyun
 * 2019/1/8 23:06
 */
// 注入spring容器
@Component
// 定义filterName 和过滤的url
@WebFilter(filterName = "myFilter" ,urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // do something 处理request 或response
        System.out.println("----------MyFilter-----------");
        // 调用filter链中的下一个filter
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
