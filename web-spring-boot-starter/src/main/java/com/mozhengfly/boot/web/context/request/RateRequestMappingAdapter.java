/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.context.request.RateRequestMappingAdapter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.context.request;

import com.mozhengfly.boot.web.rate.IRateStrategy;
import com.mozhengfly.boot.web.rate.RateLimit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

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

    private IRateStrategy rateStrategy;

    @Override
    public RequestMappingInfo adapter(Method method, Class<?> handlerType, RequestMappingInfo info) {
        RateLimit rateLimit = AnnotatedElementUtils.findMergedAnnotation(method, RateLimit.class);
        if (rateLimit != null) {
            AtomicReference<String> currentMethod = new AtomicReference<>("ALL");
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            if (CollectionUtils.isNotEmpty(methods)) {
                methods.forEach(m -> currentMethod.set(m.name()));
            }
            info.getPatternsCondition().getPatterns().forEach(url -> {
                String key = currentMethod.get() + "_" + url;
                rateStrategy.addRateLimit(key, rateLimit.value());
                log.info("为url[{}]增加限流策略: 每秒[{}]次", key, rateLimit.value());
            });
        }
        return info;
    }
}
