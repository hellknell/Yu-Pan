package com.Yu.Pan.cache.test.instance;

import com.Yu.Pan.cache.core.constants.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/28 19:15
 */
@Slf4j
    @Component
    public class Tester {
        int i = 0;

    @Cacheable(cacheNames = CacheConstants.YU_PAN_CACHE, key = "#name", sync = true)
    public String get(String name) {
        log.info("缓存被读取了{}次", ++i);
        return "Hello, " + name;
    }
}