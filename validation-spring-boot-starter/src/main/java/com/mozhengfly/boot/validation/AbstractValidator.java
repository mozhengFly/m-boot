package com.mozhengfly.boot.validation;

/**
 * AbstractValidation
 *
 * @Description AbstractValidation
 * @Author wangchonglin
 * @Date 2020-01-06 14:45:39
 * @Version 1.0.0
 */
public abstract class AbstractValidator<T> {

    /**
     * 校验对象
     *
     * @param object
     * @return
     */
    public abstract boolean isValid(T object);
}
