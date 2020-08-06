package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.validator.StringFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * StringField
 *
 * @Description 字符串校验类
 * @Author wangchonglin
 * @Date 2020-07-06 14:59:51
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(StringField.List.class)
@Constraint(validatedBy = StringFieldValidator.class)
public @interface StringField {

    String message() default "[${validatedValue}]校验不通过";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 在value数组中
     * @return
     */
    String[] in() default {};

    @Documented
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        StringField[] value();
    }
}
