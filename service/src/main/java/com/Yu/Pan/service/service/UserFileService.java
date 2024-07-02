package com.Yu.Pan.service.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.Yu.Pan.core.constants.YuPanConstants;
import com.Yu.Pan.core.exception.BizException;
import com.Yu.Pan.core.generator.help.MyBatisWrapper;
import com.Yu.Pan.core.utils.IdUtil;
import com.Yu.Pan.service.constants.CreateFolderConstants;
import com.Yu.Pan.service.context.CreateFolderContext;
import com.Yu.Pan.service.domain.UserFile;
import com.Yu.Pan.service.domain.UserFileField;
import com.Yu.Pan.service.enums.DelFlagEnum;
import com.Yu.Pan.service.enums.FolderFlagEnum;
import com.Yu.Pan.service.mapper.UserFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 20:14
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserFileService {
    final UserFileMapper userFileMapper;

    public Long createUserRoot(CreateFolderContext context) {
        return saveUserFile(context.getUserId(), context.getParentId(), context.getFolderName(), null, null, FolderFlagEnum.TRUE, null);
    }

    private Long saveUserFile(Long userId, Long parentId, String fileName, String fileType, Long realFilePath, FolderFlagEnum flagEnum, String fileSizeDesc) {
        UserFile userFile = assemblyUserFile(userId, parentId, fileName, fileType, realFilePath, flagEnum, fileSizeDesc);
        if (ObjectUtil.isNotNull(userFile)) {
            try {
                if (userFileMapper.insert(userFile) != -1) {
                    return userFile.getFileId();
                }
            } catch (DuplicateKeyException e) {
                throw new BizException("创建用户文件失败");
            }
        }
        return null;
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
        handleDuplicateFileName(userFile);
        return userFile;
    }

    private void handleDuplicateFileName(UserFile userFile) {
        String oldFileName = userFile.getFilename(), newFileNameWithoutExt, fileNameExt;

        int pos = oldFileName.lastIndexOf(YuPanConstants.POINT_STR);
        if (pos != -1) {
            newFileNameWithoutExt = oldFileName.substring(YuPanConstants.ZERO_INT, pos);
            fileNameExt = oldFileName.replace(newFileNameWithoutExt, YuPanConstants.EMPTY_STR);
        } else {
            newFileNameWithoutExt = oldFileName;
            fileNameExt = YuPanConstants.EMPTY_STR;
        }
        int count = countDuplicateFileName(userFile, newFileNameWithoutExt);
        if (count == 0) {
            return;
        }
        String s = assemblyNewFileName(newFileNameWithoutExt, fileNameExt, count);
        userFile.setFilename(s);
    }

    private String assemblyNewFileName(String newFileNameWithoutExt, String fileNameExt, int count) {
        StringBuffer sb = new StringBuffer(newFileNameWithoutExt);
        sb.append(CreateFolderConstants.LEFT_PARENTHESES_STR).append(count).append(CreateFolderConstants.RIGHT_PARENTHESES_STR).append(fileNameExt);
        return sb.toString();
    }

    private int countDuplicateFileName(UserFile userFile, String newFileNameWithoutExt) {
        MyBatisWrapper<UserFile> wrapper = new MyBatisWrapper<>();
        wrapper.select(UserFileField.UserId).whereBuilder().andEq(UserFileField.setUserId(userFile.getUserId())).andEq(UserFileField.setParentId(userFile.getParentId())).andEq(UserFileField.setFilename(newFileNameWithoutExt)).andEq(UserFileField.setFolderFlag(userFile.getFolderFlag())).andEq(UserFileField.setDelFlag(DelFlagEnum.NO.getFlag())).andLike(UserFileField.setFilename(newFileNameWithoutExt));
        return userFileMapper.count(wrapper);
    }
}