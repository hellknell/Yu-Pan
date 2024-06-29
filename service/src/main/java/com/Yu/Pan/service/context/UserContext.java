package com.Yu.Pan.service.context;

import com.Yu.Pan.service.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 19:41
 */
@Data
public class UserContext implements Serializable {
    private static final long serialVersionUID = -535345135532515L;

    private String username;
    private String password;
    private String question;
    private String answer;
    private User user;
}
