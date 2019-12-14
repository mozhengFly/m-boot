package com.mozhengfly.boot.verification.annotation;

import com.mozhengfly.boot.verification.AbstractVerification;
import com.mozhengfly.boot.verification.type.DefaultObjectVerification;

import java.lang.annotation.*;

/**
 * @Description ParamVerification
 * @Author mozhengfly
 * @Date 2019-07-01 21:47:19
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectVerification {

    Class<? extends AbstractVerification> value() default DefaultObjectVerification.class;
}
