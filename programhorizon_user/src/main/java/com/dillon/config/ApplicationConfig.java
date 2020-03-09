package com.dillon.config;

import com.dillon.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 7:46 PM
 */
/*
创建拦截器配置类，具体用来定义要拦截的拦截器类以及拦截路径
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login/**")
                .excludePathPatterns("/user/login/**");
    }
}
