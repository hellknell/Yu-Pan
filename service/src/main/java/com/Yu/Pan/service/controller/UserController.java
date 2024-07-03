package com.Yu.Pan.service.controller;

import com.Yu.Pan.core.response.R;
import com.Yu.Pan.service.context.*;
import com.Yu.Pan.service.converter.UserConverter;
import com.Yu.Pan.service.req.*;
import com.Yu.Pan.service.resp.UserInfoResp;
import com.Yu.Pan.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/register")
    public R register(@Validated @RequestBody UserRegReq req) throws NoSuchAlgorithmException {
        UserContext userContext = UserConverter.INSTANCE.toUserContext(req);
        log.info("userContext:{}", userContext.getPassword());
        userService.register(userContext);
        return R.success();
    }
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R<String> login(@Validated @RequestBody UserLoginReq req) throws NoSuchAlgorithmException {
        UserLoginContext context = UserConverter.INSTANCE.toUserLoginContext(req);
        return R.success(userService.login(context));
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/")
    public R<UserInfoResp> info()  {
        Long userId = LoginMemberContext.getUserId();
        return R.success(userService.info(userId));
    }

    @ApiOperation(value = "用户登出")
    @PostMapping("/exit")
    public R logout() {
        Long userId = LoginMemberContext.getUserId();
        userService.exit(userId);
        return R.success();
    }

    @ApiOperation(value = "用户忘记密码--校验用户名")
    @PostMapping("/username/check")
    public R<String> checkName(@Validated @RequestBody CheckNameReq req) {
        CheckNameContext context = UserConverter.INSTANCE.toCheckNameContext(req);
        return R.success(userService.checkName(context));
    }

    @ApiOperation(value = "用户忘记密码--校验密码答案")
    @PostMapping("/answer/check")
    public R checkAnswer(@Validated @RequestBody CheckAnswerReq req) {
        CheckAnswerContext context = UserConverter.INSTANCE.toCheckAnswerContext(req);
        return R.success(userService.checkAnswer(context));
    }

    @ApiOperation(value = "用户忘记密码--重置密码")
    @PostMapping("/password/reset")
    public R resetPassword(@Validated @RequestBody ResetPasswordReq req) {
        ResetPasswordContext resetPasswordContext = UserConverter.INSTANCE.toResetPasswordContext(req);
        userService.resetPassword(resetPasswordContext);
        return R.success();
    }

    @ApiOperation(value = "用户在线修改密码")
    @PostMapping("/password/change")
    public R changePassword(@Validated @RequestBody ChangePasswordReq req) throws NoSuchAlgorithmException {
        ChangePasswordContext context = UserConverter.INSTANCE.toChangePasswordContext(req);
        context.setUserId(LoginMemberContext.getUserId());
        log.info("id:{}", LoginMemberContext.getUserId());
        userService.changePassword(context);
        return R.success();
    }
}
