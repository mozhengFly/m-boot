package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.annotation.ConditionalRequired;
import com.mozhengfly.boot.validation.exception.CustomValidationException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * ConditionalRequiredValidator
 *
 * @Description ConditionalRequiredValidator
 * @Author wangchonglin
 * @Date 2020-01-06 19:35:26
 * @Version 1.0.0
 */
public class ConditionalRequiredValidator implements ConstraintValidator<ConditionalRequired, Object> {

    private String field;

    private String condition;

    @Override
    public void initialize(ConditionalRequired constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.condition = constraintAnnotation.condition();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression conditionExpression = parser.parseExpression(this.condition);
        Boolean res = conditionExpression.getValue(new StandardEvaluationContext(value), Boolean.class);
        // 条件校验成功，才进行进一步的条件验证
        if (res != null && res) {
            try {
                Object fieldValue = PropertyUtils.getProperty(value, this.field);
                return !ObjectUtils.isEmpty(fieldValue);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new CustomValidationException(String.format("获取字段[%s]属性值出错", this.field), e);
            }
        }
        // 条件不满足 认为成功
        return true;
    }

}
