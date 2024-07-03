package com.Yu.Pan.service.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/3 18:38
 */
@Data
public class UserInfoReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String rootFileId;
    private String rootFilename;
}