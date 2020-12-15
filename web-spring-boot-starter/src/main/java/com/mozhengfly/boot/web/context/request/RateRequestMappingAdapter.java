/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.context.request.RateRequestMappingAdapter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.context.request;

import com.mozhengfly.boot.web.rate.RateLimit;
import com.mozhengfly.boot.web.rate.RateManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;

/**
 * RateRequestMappingAdapter
 * @Description 限流
 * @Author wangchonglin
 * @Date 2020-12-14 20:51:53
 * @Version 1.0.0
 */
@Slf4j
@AllArgsConstructor
public class RateRequestMappingAdapter implements IRequestMappingAdapter {

    private RateManager rateManager;

    @Override
    public RequestMappingInfo adapter(Method method, Class<?> handlerType, RequestMappingInfo info) {
        RateLimit rateLimit = AnnotatedElementUtils.findMergedAnnotation(method, RateLimit.class);
        if (rateLimit != null) {
            log.info("xianliu : {}", info.getPatternsCondition().getPatterns());
            log.info("xianliu : {}", rateLimit.permitsPerSecond());
        }
        return info;
    }
}
