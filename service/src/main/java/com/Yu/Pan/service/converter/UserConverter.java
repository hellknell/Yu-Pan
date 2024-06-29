package com.Yu.Pan.service.converter;

import com.Yu.Pan.service.context.UserContext;
import com.Yu.Pan.service.domain.User;
import com.Yu.Pan.service.req.UserRegReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 11:19
 */
/*
用户实体转化工具类
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserContext toUserContext(UserRegReq userRegReq);


    @Mapping(target = "password", ignore = true)
    User toUser(UserContext userContext);

}
