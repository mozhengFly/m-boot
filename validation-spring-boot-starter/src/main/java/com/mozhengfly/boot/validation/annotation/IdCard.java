package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * IdCard
 *
 * @author mozhengfly
 * @version 1.0.0
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
