package com.Yu.Pan.service.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/2 16:34
 *
 * @author lenovo
 */
@Data
@ApiModel("重置密码")
public class ResetPasswordReq implements Serializable {
    private final static long serialVersionUID = 1L;
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Length(min = 8, max = 16, message = "请输入长度为8-16位的密码")
    @ApiModelProperty("新密码")
    private String password;

    @ApiModelProperty("token")
    private String token;
}
