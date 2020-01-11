package com.dillon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/8/2020 8:04 PM
 */
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;  // 优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;   // 是否执行该过滤器，此处为true，说明需要过滤
      }

    @Override
    public Object run() throws ZuulException {
        System.out.println("过滤器执行了");
        return null;
    }
}
