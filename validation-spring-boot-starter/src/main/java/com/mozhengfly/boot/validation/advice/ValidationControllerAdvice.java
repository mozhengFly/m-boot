package com.mozhengfly.boot.validation.advice;

import com.mozhengfly.boot.validation.ResultInfo;
import com.mozhengfly.boot.validation.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ValidationControllerAdvice
 *
 * @Description ValidationControllerAdvice
 * @Author wangchonglin
 * @Date 2020-01-07 13:59:10
 * @Version 1.0.0
 */
@Slf4j
@ControllerAdvice
public class ValidationControllerAdvice {

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultInfo customValidationException(HttpServletRequest request, CustomValidationException e) {
        return new ResultInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultInfo bindException(HttpServletRequest request, BindException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return new ResultInfo(HttpStatus.BAD_REQUEST.value(), getMessage(errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultInfo methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return new ResultInfo(HttpStatus.BAD_REQUEST.value(), getMessage(errors));
    }

    private String getMessage(List<ObjectError> errors) {
        if (CollectionUtils.isEmpty(errors)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        errors.stream().forEach(error -> {
            sb.append(error.getDefaultMessage()).append("；");
        });
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
