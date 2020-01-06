package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.annotation.IdCard;
import com.mozhengfly.boot.validation.util.IdCardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * IdCardValidator
 *
 * @Description IdCardValidator
 * @Author wangchonglin
 * @Date 2020-01-06 16:13:13
 * @Version 1.0.0
 */
@Slf4j
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    @Override
    public void initialize(IdCard constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 只在身份号有值的时候才校验 没值的时候不校验
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        return IdCardUtil.isIdCard(value);
    }
}
