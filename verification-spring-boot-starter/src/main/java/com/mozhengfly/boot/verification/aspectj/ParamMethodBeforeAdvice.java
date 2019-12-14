package com.mozhengfly.boot.verification.aspectj;

import com.mozhengfly.boot.verification.AbstractVerification;
import com.mozhengfly.boot.verification.ParamVerificationException;
import com.mozhengfly.boot.verification.VerificationResult;
import com.mozhengfly.boot.verification.annotation.ObjectVerification;
import com.mozhengfly.boot.verification.annotation.ParamVerification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description ParamMethodBeforeAdvice
 * @Author mozhengfly
 * @Date 2019-07-01 21:45:09
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
public class ParamMethodBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] params, Object o) throws ParamVerificationException {
        for (Object object: params) {
            Optional.ofNullable(object).ifPresent(paramObject -> {
                Field[] fields = paramObject.getClass().getDeclaredFields();
                // 处理类字段注解
                ObjectVerification objectVerification = paramObject.getClass().getAnnotation(ObjectVerification.class);
                Optional.ofNullable(objectVerification).ifPresent(verification -> {
                    verifyValue(verification.value(), paramObject);
                });
                // 处理对象属性字段的注解
                for (Field field: fields) {
                    ParamVerification paramVerification = field.getAnnotation(ParamVerification.class);
                    Optional.ofNullable(paramVerification).ifPresent(verification -> {
                        Object value = getValueByField(field, paramObject);
                        verifyRequired(value, field, verification);
                        verifyParamLength(value, field, verification);
                        verifyCustomType(value, verification);
                    });
                }
            });
        }
    }


    /**
     * 验证必填
     * @param value
     * @param field
     * @param paramVerification
     * @throws ParamVerificationException
     */
    private void verifyRequired(Object value, Field field, ParamVerification paramVerification) throws ParamVerificationException{
        boolean required = paramVerification.required();
        if (required) {
            String tipMessage = String.format("%s字段的值为必填", field.getName());
            Optional<Object> object = Optional.ofNullable(value);
            if (!object.isPresent()) {
                throw new ParamVerificationException(tipMessage);
            }
            object.ifPresent(data -> {
                if (data instanceof String && StringUtils.isBlank((String)data)) {
                    throw new ParamVerificationException(tipMessage);
                }
            });
        }
    }

    /**
     * 校验长度
     * @param value
     * @param field
     * @param paramVerification
     * @throws ParamVerificationException
     */
    private void verifyParamLength(Object value, Field field, ParamVerification paramVerification) throws ParamVerificationException {
        String fieldName = field.getName();
        // 得到对象的长度，默认-1视为无效长度
        int length = -1;
        if (value instanceof String) {
            length = StringUtils.length((String)value);
        } else if (isCollection(value)){
            length = CollectionUtils.size(value);
        }
        // 校验最大长度限制
        int max = paramVerification.max();
        if (max > 0 && length >= 0) {
            String tipMessage = String.format("%s字段的最大长度为%s", fieldName, max);
            if (length > max) {
                throw new ParamVerificationException(tipMessage);
            }
        }
        // 校验最小长度限制
        int min = paramVerification.min();
        if (min >= 0 && length >= 0) {
            String tipMessage = String.format("%s字段的最小长度为%s", fieldName, min);
            if (length < min) {
                throw new ParamVerificationException(tipMessage);
            }
        }
    }

    /**
     * 判断是否能够识别长度
     * @param object
     * @return
     */
    private boolean isCollection(Object object) {
        return object instanceof Map || object instanceof Collection || object instanceof Object[] || object instanceof Iterator || object instanceof Enumeration;
    }

    /**
     * 自定义校验类
     * @param value
     * @param paramVerification
     * @throws ParamVerificationException
     */
    private void verifyCustomType(Object value, ParamVerification paramVerification) throws ParamVerificationException {
        Class<? extends AbstractVerification>[] clazzArray = paramVerification.types();
        if (ArrayUtils.isEmpty(clazzArray)) {
            // 没有配置 不进行自定义校验
            return;
        }
        for (Class<? extends AbstractVerification> clazz: clazzArray) {
            verifyValue(clazz, value);
        }
    }

    /**
     * 调用自定义的类进行校验
     * @param clazz
     * @param value
     */
    private void verifyValue(Class<? extends AbstractVerification> clazz, Object value) {
        try {
            AbstractVerification verification = clazz.newInstance();
            VerificationResult result = verification.verifyValue(value);
            if (!result.isSuccess()) {
                throw new ParamVerificationException(result.getMessage());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ParamVerificationException(e.getMessage(), e);
        }
    }

    /**
     * 反射取值
     * @param field
     * @param object
     * @return
     */
    private Object getValueByField(Field field, Object object) {
        String objectName = object.getClass().getName();
        String fieldName = field.getName();
        try {
            field.setAccessible(true);
            Object value = field.get(object);
            log.debug("[{}]对象的[{}]属性的值为[{}]", objectName, fieldName, value);
            return value;
        } catch (IllegalAccessException e) {
            String tipMessage = String.format("从[%s]对象中获取[%s]字段值失败", objectName, fieldName);
            throw new ParamVerificationException(tipMessage, e);
        }
    }
}
