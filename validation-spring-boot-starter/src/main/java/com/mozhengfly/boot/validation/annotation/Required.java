package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.validator.RequiredValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Required
 *
 * @Description Required
 * @Author wangchonglin
 * @Date 2020-01-06 16:12:18
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Required.List.class)
@Constraint(validatedBy = RequiredValidator.class)
public @interface Required {

    boolean required() default true;

    String message() default "{javax.validation.constraints.NotEmpty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Documented
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        Required[] value();
    }
}
