package com.Yu.Pan.utils;

import com.Yu.Pan.core.utils.AES128Util;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 16:19
 */
@SpringBootTest
public class core {
    @Test
    public void encrypt() {
        byte[] encrypt = AES128Util.encrypt("你是谁?".getBytes());
        System.out.println("加密后:" +new String(encrypt));
        System.out.println("=================================================");
        byte[] decrypt = AES128Util.decrypt(encrypt);
        System.out.println("解密后:" +new String(decrypt));
    }
}
