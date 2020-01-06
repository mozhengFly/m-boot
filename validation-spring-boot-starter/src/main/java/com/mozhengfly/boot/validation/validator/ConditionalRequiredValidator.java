package com.mozhengfly.boot.validation.validator;

import com.mozhengfly.boot.validation.annotation.ConditionalRequired;
import com.mozhengfly.boot.validation.exception.CustomValidationException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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
        try {
            Object fieldValue = PropertyUtils.getProperty(value, this.field);
            Object res = execExpression(this.condition, PropertyUtils.describe(value));
            // 条件校验成功
            if (res != null && res instanceof Boolean && (Boolean )res) {
                return !ObjectUtils.isEmpty(fieldValue);
            }
            // 条件不满足 认为成功
            return true;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CustomValidationException("获取字段属性值出错", e);
        }
    }

    public Object execExpression(String expression, Map<String, Object> map) {
        Expression e = new JexlEngine().createExpression(expression);
        JexlContext jexlContext = new MapContext();
        map.entrySet().forEach(entry -> {
            jexlContext.set(entry.getKey(), entry.getValue());
        });
        return e.evaluate(jexlContext);
    }

}
