package com.Yu.Pan.service.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/30 19:53
 */
@Data
public class CheckNameContext implements Serializable {
    private final Long serialVersionUID = 1L;
    private String username;
}
