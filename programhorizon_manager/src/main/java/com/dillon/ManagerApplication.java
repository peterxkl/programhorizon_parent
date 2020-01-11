package com.dillon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/8/2020 7:30 PM
 */
@SpringBootApplication
@EnableZuulProxy
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
