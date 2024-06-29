package com.Yu.Pan.service.config;


import com.Yu.Pan.core.constants.YuPanConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 18:27
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = YuPanConstants.BASE_COMPONENT_SCAN_PATH + ".service.mapper")
@ComponentScan(basePackages = YuPanConstants.BASE_COMPONENT_SCAN_PATH)
public class YuPanApplication {
    public static void main(String[] args) {
        SpringApplication.run(YuPanApplication.class, args);
    }
}
