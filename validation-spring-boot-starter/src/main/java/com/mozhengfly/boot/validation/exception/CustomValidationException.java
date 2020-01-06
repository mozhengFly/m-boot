package com.mozhengfly.boot.validation.exception;
/**
 * CustomValidationException
 * @Description 自定义验证异常
 * @Author wangchonglin
 * @Date 2020-01-06 17:02:29
 * @Version 1.0.0
 */
public class CustomValidationException extends RuntimeException{

    private static final long serialVersionUID = -5945005768251722951L;

    public CustomValidationException() {
        super();
    }

    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
