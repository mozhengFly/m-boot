package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.AbstractCustomValidator;
import com.mozhengfly.boot.validation.annotation.CustomValidation;
import com.mozhengfly.boot.validation.exception.CustomValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * CustomValidator
 *
 * @Description CustomValidator
 * @Author wangchonglin
 * @Date 2020-01-06 14:19:13
 * @Version 1.0.0
 */
public class CustomValidator implements ConstraintValidator<CustomValidation, Object> {

    private Class<? extends AbstractCustomValidator> proxy;

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        this.proxy = constraintAnnotation.proxy();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            AbstractCustomValidator abstractValidator = this.proxy.newInstance();
            return abstractValidator.isValid(value);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CustomValidationException(String.format("根据类[%s]实例化对象出错", this.proxy.getName()), e);
        }
    }
}
