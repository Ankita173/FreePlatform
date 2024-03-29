package com.platform.dataaccess.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import org.springframework.cache.Cache;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
    	SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache cache = new ConcurrentMapCache("product");
        cacheManager.setCaches(Arrays.asList(cache));
        return cacheManager;
    }
}
