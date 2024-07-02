package com.Yu.Pan.service.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/30 19:53
 */
@Data
@ApiModel("用户登录请求")
public class UserLoginReq implements Serializable {
    private final Long serialVersionUID = 1L;
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "用户名格式错误")
    private String username;


    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    @Length(min = 8, max = 16, message = "请输入长度为8-16位的密码")
    private String password;


}
