package com.Yu.Pan.service.context;

import com.Yu.Pan.service.domain.User;
import lombok.Data;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/2 21:42
 */
@Data
public class ChangePasswordContext {
    private Long userId;
    private String password;
    private String newPassword;
    private User user;
}
