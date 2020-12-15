package com.mozhengfly.boot.web.annotation;

import com.mozhengfly.boot.web.configuration.RequestMappingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 是否开启APIVersion
 * @Author mozhengfly
 * @Date 2019-12-15 01:49:16
 * @Version V1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RequestMappingConfiguration.class)
@Documented
public @interface EnableApiVersion {
}
