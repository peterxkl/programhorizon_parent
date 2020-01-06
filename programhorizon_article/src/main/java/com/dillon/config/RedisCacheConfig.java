package com.dillon.config;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/1/2019 2:09 PM
 * 该类是用来定义缓存配置的
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheConfig {
    @Autowired
    RedisConnectionFactory connectionFactory;  //RedisConnectionFactory是JedisConnectionFactory的父类
    @Bean
    RedisCacheManager redisCacheManager() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .prefixKeysWith("sang:")            //
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(30));  //缓存过期时间
        configurationMap.put("c1",redisCacheConfiguration);  //“c1”为缓存名称
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        RedisCacheManager redisCacheManager = new RedisCacheManager(
                cacheWriter,
                RedisCacheConfiguration.defaultCacheConfig(),
                configurationMap);
        return redisCacheManager;
    }
}
