package com.Yu.Pan.service.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/1 10:21
 */
@Data
public class UserInfoResp implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long username;
    private Long rootFileId;
    private String rootFilename;
}
