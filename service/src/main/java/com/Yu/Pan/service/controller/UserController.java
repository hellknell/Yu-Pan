package com.Yu.Pan.service.controller;

import com.Yu.Pan.core.response.R;
import com.Yu.Pan.service.context.UserContext;
import com.Yu.Pan.service.converter.UserConverter;
import com.Yu.Pan.service.req.UserRegReq;
import com.Yu.Pan.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 11:07
 */
@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "用户模块")
public class UserController {
    final UserService userService;

    @ApiOperation(value = "用户注册", notes = "实现了接口幂等性,可多次调用")
    @PostMapping("/reg")
    public R<Void> register(@Validated @RequestBody UserRegReq req) throws NoSuchAlgorithmException {
        UserContext userContext = UserConverter.INSTANCE.toUserContext(req);
        userService.register(userContext);
        return R.success();
    }
}
