package com.Yu.Pan.service.service;

import cn.hutool.core.date.DateTime;
import com.Yu.Pan.core.utils.IdUtil;
import com.Yu.Pan.service.context.CreateFolderContext;
import com.Yu.Pan.service.domain.UserFile;
import com.Yu.Pan.service.enums.DelFlagEnum;
import com.Yu.Pan.service.enums.FolderFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 20:14
 */
@Service
@Slf4j
public class UserFileService {
    public Long createUserRoot(CreateFolderContext context) {
        return saveUserFile(context.getUserId(), context.getParentId(), context.getFolderName(), null, null, FolderFlagEnum.TRUE, null);
    }

    private Long saveUserFile(Long userId, Long parentId, String fileName, String fileType, Long realFilePath, FolderFlagEnum flagEnum, String fileSizeDesc) {
        return assemblyUserFile(userId, parentId, fileName, fileType, realFilePath, flagEnum, fileSizeDesc).getFileId();

    }

    private UserFile assemblyUserFile(Long userId, Long parentId, String fileName, String fileType, Long realFileId, FolderFlagEnum flagEnum, String fileSizeDesc) {
        UserFile userFile = new UserFile();
        DateTime now = DateTime.now();
        userFile.setUserId(userId);
        userFile.setParentId(parentId);
        userFile.setFilename(fileName);
        userFile.setFileSizeDesc(fileSizeDesc);
        userFile.setFileType(fileType);
        userFile.setFileId(IdUtil.getSnowFlaskId());
        userFile.setRealFileId(realFileId);
        userFile.setFolderFlag(flagEnum.getFlag());
        userFile.setDelFlag(DelFlagEnum.NO.getFlag());
        userFile.setCreateTime(now);
        userFile.setUpdateTime(now);
        userFile.setUpdateUser(userId);
        userFile.setCreateUser(userId);

        return userFile;
    }
}