package com.mozhengfly.boot.web.annotation;

import java.lang.annotation.*;

/**
 * ApiVersion
 *
 * @Description api annotation
 * @Author mozhengfly
 * @Date 2019-12-13 14:43:58
 * @Version 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    String prefix() default "api";

    int value() default 1;
}
