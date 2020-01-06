package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * IdCard
 *
 * @Description 只校验身份号
 * @Author wangchonglin
 * @Date 2020-01-06 16:12:18
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardValidator.class)
public @interface IdCard {

    String message() default "身份证号码[${validatedValue}]格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
