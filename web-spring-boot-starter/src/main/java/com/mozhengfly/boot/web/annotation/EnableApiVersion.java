package com.mozhengfly.boot.web.annotation;

import com.mozhengfly.boot.web.configuration.VersionWebMvcRegistrations;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 是否开启APIVersion
 * @Author mozhengfly
 * @Date 2019-12-15 01:49:16
 * @Version V1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(VersionWebMvcRegistrations.class)
@Documented
public @interface EnableApiVersion {
}
