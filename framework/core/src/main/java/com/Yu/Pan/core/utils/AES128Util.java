package com.Yu.Pan.core.utils;

import com.Yu.Pan.core.constants.YuPanConstants;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 15:50
 */
public class AES128Util {
    /*
     *默认向量常量
     */
    public static final String IV = "akjsfakjshf@#!~&";
    private static final String P_KEY = StringUtils.reverse(IV);
    private static final String AES_STR = "AES";
    private static final String INSTANCE_STR = "AES/CBC/PKCS5Padding";

    //加密
    public static byte[] encrypt(byte[] content) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(P_KEY.getBytes(), AES_STR);
            Cipher cipher = Cipher.getInstance(INSTANCE_STR);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(content);
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] content) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(P_KEY.getBytes(), AES_STR);
            Cipher cipher = Cipher.getInstance(INSTANCE_STR);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] res = cipher.doFinal(content);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String content) {
        try {
            if (StringUtils.isBlank(content)) {
                return YuPanConstants.EMPTY_STR;
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(P_KEY.getBytes(), AES_STR);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(INSTANCE_STR);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] bytes = cipher.doFinal(content.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return YuPanConstants.EMPTY_STR;
    }

    public static String decrypt(String content) {
        try {
            if (StringUtils.isBlank(content)) {
                return YuPanConstants.EMPTY_STR;
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(P_KEY.getBytes(), AES_STR);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(INSTANCE_STR);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return YuPanConstants.EMPTY_STR;
    }
}