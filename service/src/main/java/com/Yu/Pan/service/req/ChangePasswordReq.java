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
 * 日期：2024/7/2 21:40
 */

@Data
@ApiModel("修改密码")
public class ChangePasswordReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度为8-16位")
    private String password;
    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度为8-16位")
    private String newPassword;

}
