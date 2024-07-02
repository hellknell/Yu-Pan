package com.Yu.Pan.service.domain;
import com.Yu.Pan.core.generator.help.DbField;
import com.Yu.Pan.core.generator.help.FieldResult;

import java.util.Collections;

public class FileField {
    public static DbField Id = new DbField("id","id","BIGINT","java.lang.Long");

    public static DbField FileName = new DbField("file_name","fileName","VARCHAR","java.lang.String");

    public static DbField RealPath = new DbField("real_path","realPath","VARCHAR","java.lang.String");

    public static DbField FileSize = new DbField("file_size","fileSize","VARCHAR","java.lang.String");

    public static DbField FileSizeDesc = new DbField("file_size_desc","fileSizeDesc","VARCHAR","java.lang.String");

    public static DbField FileSuffix = new DbField("file_suffix","fileSuffix","VARCHAR","java.lang.String");

    public static DbField FilePreviewContentType = new DbField("file_preview_content_type","filePreviewContentType","VARCHAR","java.lang.String");

    public static DbField Identifier = new DbField("identifier","identifier","VARCHAR","java.lang.String");

    public static DbField CreateUser = new DbField("create_user","createUser","BIGINT","java.lang.Long");

    public static DbField CreateTime = new DbField("create_time","createTime","TIMESTAMP","java.util.Date");

    public static FieldResult setId(Long id) {
        return new FieldResult(Id, Collections.singletonList(id));
    }

    public static FieldResult setFileName(String fileName) {
        return new FieldResult(FileName, Collections.singletonList(fileName));
    }

    public static FieldResult setRealPath(String realPath) {
        return new FieldResult(RealPath, Collections.singletonList(realPath));
    }

    public static FieldResult setFileSize(String fileSize) {
        return new FieldResult(FileSize, Collections.singletonList(fileSize));
    }

    public static FieldResult setFileSizeDesc(String fileSizeDesc) {
        return new FieldResult(FileSizeDesc, Collections.singletonList(fileSizeDesc));
    }

    public static FieldResult setFileSuffix(String fileSuffix) {
        return new FieldResult(FileSuffix, Collections.singletonList(fileSuffix));
    }

    public static FieldResult setFilePreviewContentType(String filePreviewContentType) {
        return new FieldResult(FilePreviewContentType, Collections.singletonList(filePreviewContentType));
    }

    public static FieldResult setIdentifier(String identifier) {
        return new FieldResult(Identifier, Collections.singletonList(identifier));
    }

    public static FieldResult setCreateUser(Long createUser) {
        return new FieldResult(CreateUser, Collections.singletonList(createUser));
    }

    public static FieldResult setCreateTime(java.util.Date createTime) {
        return new FieldResult(CreateTime, Collections.singletonList(createTime));
    }
}