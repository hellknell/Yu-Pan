package com.Yu.Pan.service.resp;


import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/30 19:56
 */

@Data
public class UserResp implements Serializable {
    private final long serialVersionUID = 1L;
    private String username;
    private Long id;
}
