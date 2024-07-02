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
@MapperScan(basePackages = "com.Yu.Pan.service.mapper")
@ComponentScan(basePackages = "com.Yu.Pan")
public class YuPanApplication {
    public static void main(String[] args) {
        SpringApplication.run(YuPanApplication.class, args);
    }
}
