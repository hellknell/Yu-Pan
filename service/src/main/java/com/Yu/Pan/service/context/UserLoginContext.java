package com.Yu.Pan.service.context;

import com.Yu.Pan.service.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/30 20:42
 */
@Data
public class UserLoginContext implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String accessToken;
    private User user;

}
