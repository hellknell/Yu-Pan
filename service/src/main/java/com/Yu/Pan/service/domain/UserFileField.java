package com.Yu.Pan.service.domain;

import com.Yu.Pan.core.generator.help.DbField;
import com.Yu.Pan.core.generator.help.FieldResult;

import java.util.Collections;

public class UserFileField {
    public static DbField FileId = new DbField("file_id","fileId","BIGINT","java.lang.Long");

    public static DbField UserId = new DbField("user_id","userId","BIGINT","java.lang.Long");

    public static DbField ParentId = new DbField("parent_id","parentId","BIGINT","java.lang.Long");

    public static DbField RealFileId = new DbField("real_file_id","realFileId","BIGINT","java.lang.Long");

    public static DbField Filename = new DbField("filename","filename","VARCHAR","java.lang.String");

    public static DbField FolderFlag = new DbField("folder_flag","folderFlag","BIT","java.lang.Boolean");

    public static DbField FileType = new DbField("file_type","fileType","CHAR","java.lang.String");

    public static DbField FileSizeDesc = new DbField("file_size_desc","fileSizeDesc","VARCHAR","java.lang.String");

    public static DbField DelFlag = new DbField("del_flag","delFlag","BIT","java.lang.Boolean");

    public static DbField CreateUser = new DbField("create_user","createUser","BIGINT","java.lang.Long");

    public static DbField CreateTime = new DbField("create_time","createTime","TIMESTAMP","java.util.Date");

    public static DbField UpdateUser = new DbField("update_user","updateUser","BIGINT","java.lang.Long");

    public static DbField UpdateTime = new DbField("update_time","updateTime","TIMESTAMP","java.util.Date");

    public static FieldResult setFileId(Long fileId) {
        return new FieldResult(FileId, Collections.singletonList(fileId));
    }

    public static FieldResult setUserId(Long userId) {
        return new FieldResult(UserId, Collections.singletonList(userId));
    }

    public static FieldResult setParentId(Long parentId) {
        return new FieldResult(ParentId, Collections.singletonList(parentId));
    }

    public static FieldResult setRealFileId(Long realFileId) {
        return new FieldResult(RealFileId, Collections.singletonList(realFileId));
    }

    public static FieldResult setFilename(String filename) {
        return new FieldResult(Filename, Collections.singletonList(filename));
    }

    public static FieldResult setFolderFlag(Boolean folderFlag) {
        return new FieldResult(FolderFlag, Collections.singletonList(folderFlag));
    }

    public static FieldResult setFileType(String fileType) {
        return new FieldResult(FileType, Collections.singletonList(fileType));
    }

    public static FieldResult setFileSizeDesc(String fileSizeDesc) {
        return new FieldResult(FileSizeDesc, Collections.singletonList(fileSizeDesc));
    }

    public static FieldResult setDelFlag(Boolean delFlag) {
        return new FieldResult(DelFlag, Collections.singletonList(delFlag));
    }

    public static FieldResult setCreateUser(Long createUser) {
        return new FieldResult(CreateUser, Collections.singletonList(createUser));
    }

    public static FieldResult setCreateTime(java.util.Date createTime) {
        return new FieldResult(CreateTime, Collections.singletonList(createTime));
    }

    public static FieldResult setUpdateUser(Long updateUser) {
        return new FieldResult(UpdateUser, Collections.singletonList(updateUser));
    }

    public static FieldResult setUpdateTime(java.util.Date updateTime) {
        return new FieldResult(UpdateTime, Collections.singletonList(updateTime));
    }
}