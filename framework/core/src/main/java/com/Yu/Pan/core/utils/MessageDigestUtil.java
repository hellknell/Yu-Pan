package com.Yu.Pan.core.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.Yu.Pan.core.constants.YuPanConstants;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 16:46
 */
public class MessageDigestUtil {
    private static final String MD5_STR = "MD5";
    private static final String SHA1_STR = "SHA1";
    private static final String SHA_256 = "SHA-256";

    public static byte[] encrypt(byte[] data, String mode) throws NoSuchAlgorithmException {
        if (ArrayUtil.isEmpty(data) || StrUtil.isBlank(mode)) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        MessageDigest instance = MessageDigest.getInstance(mode);
        return  instance.digest(data);

    }

    public static byte[] encrypt(String data, String mode) throws NoSuchAlgorithmException {
        if (StrUtil.isBlank(data) || StrUtil.isBlank(mode)) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        return encrypt(data.getBytes(StandardCharsets.UTF_8), mode);
    }

    public static String encryptToString(String data, String mode) throws NoSuchAlgorithmException {
        String res = YuPanConstants.EMPTY_STR;
        byte[] encrypt = encrypt(data, mode);
        if (ArrayUtil.isEmpty(encrypt)) {
            return res;
        }
        res = Base64.getEncoder().encodeToString(encrypt);
        return res;
    }

    /*
     *获取sha1加密串
     *  */
    public static String getSha1(String data) throws NoSuchAlgorithmException {
        return encryptToString(data, SHA1_STR);
    }

    /*
     * 获取MD5加密串
     * */
    public static String getMD5(String data) {
        try {
            return encryptToString(data, MD5_STR);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSha256(String data) throws NoSuchAlgorithmException {
        return encryptToString(data, SHA_256);
    }
}
