package com.Yu.Pan.core.exception;

import com.Yu.Pan.core.response.ResponseCode;
import lombok.Data;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 15:45
 */
@Data
public class BizException extends RuntimeException {
    private Integer code;
    private String msg;

    public BizException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public BizException(String msg) {
        this.code = ResponseCode.ERROR.getCode();
        this.msg = msg;
    }

}
