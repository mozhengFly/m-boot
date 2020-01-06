package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.constants.OperatorEnum;
import com.mozhengfly.boot.validation.validator.DateCompareValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * DateCompare
 * 校验规则： 只支持 String Date LocalDate LocalDatetime 4种类型的校验
 * String类型的会转成LocalDate进行比较
 * 规则一： start 或者 end 字段的值只要有一个为空 就认为校验成功
 * 规则二： start 字段的值 默认小于等于 end字段的值，如果需要修改规则为小于的话 请使用operator指定规则
 * @Description 日期校验类
 * @Author wangchonglin
 * @Date 2020-01-06 16:47:06
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateCompareValidator.class)
public @interface DateCompare {

    /**
     * 开始时间字段
     */
    String start();

    /**
     * 结束时间字段
     */
    String end();

    /**
     * 操作符 < 或者 <= 默认是小于等于
     */
    OperatorEnum operator() default OperatorEnum.LESS_OR_EQUAL;

    String message() default "{start}不应该大于{end}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
