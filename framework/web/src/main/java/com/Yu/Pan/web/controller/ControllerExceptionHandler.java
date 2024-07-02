package com.Yu.Pan.web.controller;


import com.Yu.Pan.core.exception.BizException;
import com.Yu.Pan.core.exception.FrameworkException;
import com.Yu.Pan.core.response.R;
import com.Yu.Pan.core.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/3/23 19:14
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R<Object> handleException(Exception e) {
        return new R<>(ResponseCode.ERROR.getCode(), "系统出现异常,请联系管理员");
    }

    @ExceptionHandler(BizException.class)
    public R<Object> handleException(BizException e) {
        log.error("系统异常，请联系管理员", e);
        return new R<>(e.getCode(), e.getMsg());

    }
    @ExceptionHandler(FrameworkException.class)
    public R handleException(FrameworkException e) {
        log.error("系统异常，请联系管理员", e);
        return new R<>(ResponseCode.ERROR.getCode(), e.getMessage());

    }
    @ExceptionHandler(BindException.class)
    public R<Object> handleException(BindException e) {
        log.error("参数校验异常，请联系管理员", e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return new R<>(ResponseCode.ERROR.getCode(), e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected R handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("ParameterException:", ex);
        // 返回响应对象
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(p -> {
            errors.put(p.getField(), p.getDefaultMessage());
        });
        return R.error(ResponseCode.PARAMS_ERROR.getCode(), errors);
    }


    /**
     * ConstraintViolationException-jsr规范中的验证异常，嵌套检验问题
     * ConstraintViolatioalidated注解，不是加在Controller类的方法上nException：作用于 @NotBlank @NotNull @NotEmpty 注解，校验单个String、Integer、Collection等参数异常处理。
     * 注：Controller类上必须添加@V
     * 否则接口单个参数校验无效（RequestParam，PathVariable参数校验）
     *
     * @param ex ConstraintViolationException异常信息
     * @return 响应数据
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public R constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("ParameterException:", ex);
        // 返回响应对象
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        Map<String, String> errors = new HashMap<>();
        violations.forEach(p -> {
            String fieldName = null;
            //获取字段名称（最后一个元素才是）
            Iterator<Path.Node> nodeIterator = p.getPropertyPath().iterator();
            while (nodeIterator.hasNext()) {
                fieldName = nodeIterator.next().getName();
            }
            errors.put(fieldName, p.getMessage());
        });
        return R.error(ResponseCode.PARAMS_ERROR.getCode(), errors);
    }
}
