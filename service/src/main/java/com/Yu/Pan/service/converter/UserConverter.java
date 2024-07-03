package com.Yu.Pan.service.converter;

import com.Yu.Pan.service.context.*;
import com.Yu.Pan.service.domain.User;
import com.Yu.Pan.service.domain.UserFile;
import com.Yu.Pan.service.req.*;
import com.Yu.Pan.service.resp.UserInfoResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 11:19
 */
/*
用户实体转化工具类
 */
@Component
@Mapper(componentModel = "spring")
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserContext toUserContext(UserRegReq userRegReq);

    //    @Mapping(target = "password", ignore = true)
    User toUser(UserContext userContext);

     UserLoginContext toUserLoginContext(UserLoginReq req);

    CheckNameContext toCheckNameContext(CheckNameReq req);

    CheckAnswerContext toCheckAnswerContext(CheckAnswerReq req);

    ResetPasswordContext toResetPasswordContext(ResetPasswordReq req);

    ChangePasswordContext toChangePasswordContext(ChangePasswordReq req);

    @Mappings({@Mapping(target = "rootFileId", source = "userFile.fileId"),
            @Mapping(target = "rootFilename", source = "userFile.filename"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "avatar", source = "user.avatar"),
    })
    UserInfoResp toUserInfoResp(User user, UserFile userFile);

}
