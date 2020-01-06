package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.annotation.Required;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * RequiredValidator
 * @Description RequiredValidator
 * @Author wangchonglin
 * @Date 2020-01-06 16:13:13
 * @Version 1.0.0
 */
public class RequiredValidator implements ConstraintValidator<Required, Object> {

    private boolean required;

    @Override
    public void initialize(Required constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (this.required) {
            return !ObjectUtils.isEmpty(value);
        }
        return true;
    }
}
