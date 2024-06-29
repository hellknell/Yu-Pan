package com.Yu.Pan.cache.test;

import com.Yu.Pan.cache.core.constants.CacheConstants;
import com.Yu.Pan.cache.test.config.CaffeineConfig;
import com.Yu.Pan.cache.test.instance.Tester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/28 18:00
 */
@SpringBootTest(classes = CaffeineConfig.class)
@AutoConfigureCache
@Slf4j
@RequiredArgsConstructor
@RunWith(SpringRunner.class)
public class Test01 {
    @Resource
    private CacheManager cacheManager;
    @Autowired
    Tester tester;

    @Test
    public void test1() {
        Cache cache = cacheManager.getCache(CacheConstants.YU_PAN_CACHE);
        Assert.assertNotNull(cache);
        cache.put("name", "val");
        String name = cache.get("name", String.class);
        Assert.assertEquals("val", name);
    }

    @Test
    public void test() {
        for (int i = 0; i < 2; i++) {
            String name = tester.get("name");
            log.info(":{}", name);;
        }
    }
}


