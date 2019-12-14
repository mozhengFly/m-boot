package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.annotation.IdCard;
import com.mozhengfly.boot.validation.util.IdCardUtil;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * IdCardValidator
 * @author mozhengfly
 * @version 1.0.0
 */
@Slf4j
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    @Override
    public void initialize(IdCard constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return IdCardUtil.isIdCard(value);
    }
}
