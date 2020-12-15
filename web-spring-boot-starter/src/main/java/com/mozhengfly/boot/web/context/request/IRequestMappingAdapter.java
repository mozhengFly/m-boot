/**
 * @projectName build
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.context.request.IRequestMappingAdapter
 * @copyright Copyright 2020 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.context.request;

import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;

/**
 * IRequestMappingAdapter
 *
 * @Description 自定义适配器
 * @Author wangchonglin
 * @Date 2020-12-14 20:31:06
 * @Version 1.0.0
 */
public interface IRequestMappingAdapter {

    /**
     * 默认适配器
     * @param method {@link Method}
     * @param handlerType {@link Class}
     * @param info {@link RequestMappingInfo}
     * @return {@link RequestMappingInfo}
     */
    default RequestMappingInfo adapter(Method method, Class<?> handlerType, RequestMappingInfo info) {
        return info;
    }
}
