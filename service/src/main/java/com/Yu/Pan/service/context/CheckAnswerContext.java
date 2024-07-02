package com.Yu.Pan.service.context;

import lombok.Data;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/2 15:55
 */
@Data
public class CheckAnswerContext {
    private final static long serialVersionUID = 1L;
    private String username;
    private String question;
    private Long id;
    private String answer;
    private String token;
}
