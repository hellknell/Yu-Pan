package com.Yu.Pan.core.caffeine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/28 16:56
 */
@Component
@Data
public class CaffeineProperties {
    private Integer initialCapacity = 256;
    private Integer maxCapacity = 10000;
    private Boolean allowNullValues = Boolean.TRUE;
}
