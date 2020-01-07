package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.validator.ConditionalRequiredValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * ConditionalRequired
 *
 * @Description ConditionalRequired
 * @Author wangchonglin
 * @Date 2020-01-06 19:31:10
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ConditionalRequired.List.class)
@Constraint(validatedBy = ConditionalRequiredValidator.class)
public @interface ConditionalRequired {

    /**
     * 需要验证的必填字段
     */
    String field();

    /**
     * 条件表达式 例子：
     * '01'.equals(status) 其中status为String字段
     * 1 == status 其中status为int类型值
     * '01'.equals(status) && 1 == sfyx 状态为01并且有效
     * ('01'.equals(status) && 1 == sfyx) || 'admin'.equals(username) 状态为01并且有效 或者是username=admin
     */
    String condition();

    String message() default "{condition}表达式返回值为false";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ConditionalRequired[] value();
    }
}
