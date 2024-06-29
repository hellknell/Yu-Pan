package com.Yu.Pan.core.caffeine.config;

import com.Yu.Pan.cache.core.constants.CacheConstants;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/28 17:38
 * @author lenovo
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class CaffeineConfig {
    @Resource
    private CaffeineProperties caffeineProperties;


    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager ccm = new CaffeineCacheManager(CacheConstants.YU_PAN_CACHE);
        ccm.setAllowNullValues(caffeineProperties.getAllowNullValues());
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().initialCapacity(caffeineProperties.getInitialCapacity())
                .maximumSize(caffeineProperties.getMaxCapacity());
        ccm.setCaffeine(caffeine);
        log.info("CAFFEINE CACHE  LOADED SUCCESS:{}", ccm);
        return ccm;
    }
}
