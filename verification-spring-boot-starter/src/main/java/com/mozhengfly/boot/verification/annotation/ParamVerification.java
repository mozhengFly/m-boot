package com.mozhengfly.boot.verification.annotation;

import com.mozhengfly.boot.verification.AbstractVerification;

import java.lang.annotation.*;

/**
 * @Description ParamVerification
 * @Author mozhengfly
 * @Date 2019-07-01 21:47:19
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamVerification {

    boolean required() default true;

    int max() default -1;

    int min() default -1;

    Class<? extends AbstractVerification>[] types() default {};
}
