package com.Yu.Pan.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 18:53
 */
@RestController
@Slf4j
@Validated
@Api(tags = "测试接口")
public class TestController {
    @GetMapping("/test")
    @ApiOperation("测试接口")
    public String test(@NotBlank(message = "用户名不能为空") String username) {
        return "hellogegeg" + username;
    }
}
