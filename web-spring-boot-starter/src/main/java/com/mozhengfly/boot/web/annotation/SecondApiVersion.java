package com.mozhengfly.boot.web.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * SecondApiVersion
 *
 * @Description second api version annotation
 * @Author mozhengfly
 * @Date 2019-12-13 15:10:21
 * @Version 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiVersion(2)
public @interface SecondApiVersion {

    @AliasFor(annotation = ApiVersion.class, attribute = "prefix")
    String prefix() default "api";
}
