package com.Yu.Pan.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 14:51
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(0, "操作成功"), ERROR(1, "操作失败"),
    TOKEN_EXPIRED(2, "token过期"), PARAMS_ERROR(8, "参数错误"),
    ACCESS_DENIED(3, "访问权限不足"), NO_LOGIN(4, "未登录"),
    INVAILD_TOKEN(6, "无效的token"), USER_NOT_EXIST(7, "用户不存在");

    private Integer code;
    private String msg;
}
