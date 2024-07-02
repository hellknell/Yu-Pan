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
 * 日期：2024/7/2 15:44
 */
@Data
@ApiModel("忘记密码--校验密保答案")
public class CheckAnswerReq implements Serializable {
    private final static long serialVersionUID = 1L;
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "用户名格式错误")
    private String username;

    @ApiModelProperty(value = "密保", required = true)
    @NotBlank(message = "密保问题不能为空")
    @Length(max = 100, message = "密保答案长度不能超过100")
    private String question;

    @NotBlank(message = "密保答案不能为空")
    @Length(max = 100, message = "密保答案长度不能超过100")
    @ApiModelProperty(value = "密保答案", required = true)
    private String answer;
}
