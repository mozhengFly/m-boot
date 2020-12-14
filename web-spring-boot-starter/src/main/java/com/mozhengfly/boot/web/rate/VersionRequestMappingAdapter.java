/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.rate.VersionRequestMappingAdapter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import com.mozhengfly.boot.web.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;

/**
 * VersionRequestMappingAdapter
 *
 * @Description VersionRequestMappingAdapter
 * @Author wangchonglin
 * @Date 2020-12-14 20:40:25
 * @Version 1.0.0
 */
@Slf4j
public class VersionRequestMappingAdapter implements IRequestMappingAdapter {

    @Override
    public RequestMappingInfo adapter(Method method, Class<?> handlerType, RequestMappingInfo info) {
        // 方法上的优先级大于类上的
        ApiVersion apiVersionMethod = AnnotatedElementUtils.findMergedAnnotation(method, ApiVersion.class);
        if (apiVersionMethod != null) {
            return RequestMappingInfo.paths(getPrefixFromApiVersion(apiVersionMethod)).build().combine(info);
        }
        ApiVersion apiVersionClass = AnnotatedElementUtils.findMergedAnnotation(handlerType, ApiVersion.class);
        if (apiVersionClass != null) {
            return RequestMappingInfo.paths(getPrefixFromApiVersion(apiVersionClass)).build().combine(info);
        }
        return info;
    }

    /**
     * 处理前缀
     *
     * @param apiVersion
     * @return
     */
    private String getPrefixFromApiVersion(ApiVersion apiVersion) {
        return apiVersion.prefix() + "/v" + apiVersion.value();
    }
}
