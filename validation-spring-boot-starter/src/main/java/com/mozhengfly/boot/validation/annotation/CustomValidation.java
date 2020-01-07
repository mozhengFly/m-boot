package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.AbstractCustomValidator;
import com.mozhengfly.boot.validation.validator.CustomValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * CustomValidation
 *
 * @Description CustomValidation
 * @Author wangchonglin
 * @Date 2020-01-06 14:18:33
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CustomValidation.List.class)
@Constraint(validatedBy = CustomValidator.class)
public @interface CustomValidation {

    /**
     * 代理类 继承自 AbstractValidator
     */
    Class<? extends AbstractCustomValidator> proxy();

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        CustomValidation[] value();
    }
}
