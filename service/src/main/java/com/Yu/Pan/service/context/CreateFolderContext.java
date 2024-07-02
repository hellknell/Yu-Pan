package com.Yu.Pan.service.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 20:19
 */
@Data
public class CreateFolderContext implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long parentId;
    private String folderName;
}
