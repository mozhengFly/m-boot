package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.constants.OperatorEnum;
import com.mozhengfly.boot.validation.validator.DateCompareValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * DateCompare
 *
 * @Description DateCompare
 * @Author wangchonglin
 * @Date 2020-01-06 16:47:06
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateCompareValidator.class)
public @interface DateCompare {

    String start();

    String end();

    /**
     * 操作符 < 或者 <= 默认是小于等于
     */
    OperatorEnum operator() default OperatorEnum.LESS_OR_EQUAL;

    String message() default "{start}不应该大于{end}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
