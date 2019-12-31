package com.dillon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import util.IdWorker;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/9/2019 8:50 AM
 */
@SpringBootApplication
@EnableJpaAuditing
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
