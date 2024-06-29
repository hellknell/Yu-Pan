package com.Yu.Pan.service.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 11:10
 * @author lenovo
 */
@Data
public class UserRegReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "请输入长度为8-16位的密码")
    private String password;
    @NotBlank(message = "密保问题不能为空")
    @Length(max = 100, message = "密保问题长度不能超过100")
    @ApiModelProperty(value = "密保问题", required = true)
    private String question;
    @NotBlank(message = "密保答案不能为空")
    @Length(max = 100, message = "密保答案长度不能超过100")
    @ApiModelProperty(value = "密保答案", required = true)
    private String answer;
}
