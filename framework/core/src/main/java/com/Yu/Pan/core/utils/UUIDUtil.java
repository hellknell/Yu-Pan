package com.Yu.Pan.core.utils;

import static java.util.UUID.randomUUID;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 17:06
 */
public class UUIDUtil {
    public static String getUUID() {
        return randomUUID().toString().replace("-", "").toUpperCase();
    }
}
