package com.Yu.Pan.service.context;

import lombok.Data;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/2 16:37
 */
@Data
public class ResetPasswordContext {
    private String username;
    private String password;
    private String token;

}
