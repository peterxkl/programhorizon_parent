package com.dillon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import util.IdWorker;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 10:24 AM
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class,args);
    }
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
