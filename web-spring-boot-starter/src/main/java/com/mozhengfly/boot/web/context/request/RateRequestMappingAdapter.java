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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RateRequestMappingAdapter
 *
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
            List<String> methods = info.getMethodsCondition().getMethods().stream().map(Enum::name).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(methods)) {
                methods = this.getAllMethods();
            }
            List<String> patternUrls = new ArrayList<>(info.getPatternsCondition().getPatterns());
            if (CollectionUtils.isNotEmpty(patternUrls)) {
                methods.forEach(m -> patternUrls.forEach(url -> {
                    rateStrategy.addRateLimit(url, m, rateLimit.value());
                    log.info("为url[{}] method[{}]增加限流策略: 每秒[{}]次", url, m, rateLimit.value());
                }));
            }
        }
        return info;
    }

    private List<String> getAllMethods() {
        return Arrays.stream(RequestMethod.values()).map(Enum::toString).collect(Collectors.toList());
    }

}
