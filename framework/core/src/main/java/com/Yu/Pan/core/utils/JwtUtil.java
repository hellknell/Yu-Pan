package com.Yu.Pan.core.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/3/29 20:00
 */
@Slf4j
@Component
public class JwtUtil {
    public static String key = "2vdvv453";
    public static String creatToken(String username, Long id) {
        HashMap<String, Object> map = new HashMap<>();
        DateTime now = DateTime.now();
        DateTime expireTime = now.offsetNew(DateField.HOUR, 3);
        map.put(JWTPayload.ISSUED_AT, now);
        map.put(JWTPayload.EXPIRES_AT, expireTime);
        map.put(JWTPayload.NOT_BEFORE, now);
        map.put("username", username);
        map.put("id", id);
        String token = JWTUtil.createToken(map, key.getBytes());
        log.info("token:{}", token);
        return token;
    }

    public static boolean validateToken(String token) {
        try {
            JWT jwt = JWTUtil.parseToken(token).setKey(JwtUtil.key.getBytes());
            boolean validate = jwt.validate(0);
            log.info("校验结果:{}", validate);
            return validate;
        } catch (Exception e) {
            log.error("Jwt校验失败:", e);
            return false;
        }
    }

    public static JSONObject getMember(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payload = jwt.getPayloads();
        payload.remove(JWTPayload.ISSUED_AT);
        payload.remove(JWTPayload.NOT_BEFORE);
        payload.remove(JWTPayload.EXPIRES_AT);
        return payload;
    }
}
