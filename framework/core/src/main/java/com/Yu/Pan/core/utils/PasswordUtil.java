package com.Yu.Pan.core.utils;

import java.security.NoSuchAlgorithmException;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 17:01
 * <p>
 * }
 */
public class PasswordUtil {
    public static String genSalt() {
        return MessageDigestUtil.getMD5(UUIDUtil.getUUID());
    }

    public static String encryptPassword(String password, String salt) throws NoSuchAlgorithmException {
        return MessageDigestUtil.getSha256(MessageDigestUtil.getSha1(password) + salt);
    }
}