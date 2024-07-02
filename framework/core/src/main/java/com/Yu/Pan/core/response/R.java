package com.Yu.Pan.core.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/26 15:06
 */
/*
 *公用返回对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {
    private Integer code;
    private String message;
    private T data;
    private Map<String, String> errorMessage;

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public R(Integer code, Map<String, String> errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public R(Integer code) {
        this.code = code;
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return Objects.equals(this.code, ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success() {
        return new R<T>(ResponseCode.SUCCESS.getCode());
    }


    public static <T> R<T> success(T data) {
        return new R(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public static <T> R<T> error() {
        return new R<T>(ResponseCode.ERROR.getCode());
    }

    public static <T> R<T> error(String message) {
        return new R<>(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> R<T> error(ResponseCode responseCode) {
        return new R<>(responseCode.getCode(), responseCode.getMsg());
    }

    public static <T> R<T> error(Integer code, String message) {
        return new R<T>(code, message);
    }

    public static R error(Integer code, Map<String, String> errors) {
        return new R<>(code, errors);
    }
}