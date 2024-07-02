package com.Yu.Pan.service.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/7/2 15:18
 */
@Data
@ApiModel("校验用户名")
public class CheckNameReq implements Serializable {
    private final static long serialVersionUID = 4242341321L;
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "用户名格式错误")
    private String username;
}
