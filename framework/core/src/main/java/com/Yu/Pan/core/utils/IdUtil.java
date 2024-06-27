package com.Yu.Pan.core.utils;

import static cn.hutool.core.util.IdUtil.getSnowflake;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 16:30
 */
public class IdUtil {
    private static Long dataCenterId = 1L;
    private static Long workerId = 1L;

    public static long getSnowFlaskId() {
        return getSnowflake(dataCenterId, workerId).nextId();
    }

    public static String getSnowFlaskStr() {
        return cn.hutool.core.util.IdUtil.getSnowflake(dataCenterId, workerId).nextIdStr();
    }
}
