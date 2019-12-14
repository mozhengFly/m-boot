package com.mozhengfly.boot.web.context;

import com.mozhengfly.boot.web.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * VersionRequestMapping
 * @Description 自定义Version处理类
 * @Author mozhengfly
 * @Date 2019-12-13 14:34:26
 * @Version 1.0.0
 */
@Slf4j
public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        if (info != null) {
            // 方法上的优先级大于类上的
            ApiVersion apiVersionMethod = AnnotatedElementUtils.findMergedAnnotation(method, ApiVersion.class);
            if (apiVersionMethod != null) {
                return RequestMappingInfo.paths(getPrefixFromApiVersion(apiVersionMethod)).build().combine(info);
            }
            ApiVersion apiVersionClass = AnnotatedElementUtils.findMergedAnnotation(handlerType, ApiVersion.class);
            if (apiVersionClass != null) {
                return RequestMappingInfo.paths(getPrefixFromApiVersion(apiVersionClass)).build().combine(info);
            }
        }
        return info;
    }

    /**
     * 处理前缀
     * @param apiVersion
     * @return
     */
    private String getPrefixFromApiVersion(ApiVersion apiVersion) {
        return apiVersion.prefix() + "/v" + apiVersion.value();
    }
}
