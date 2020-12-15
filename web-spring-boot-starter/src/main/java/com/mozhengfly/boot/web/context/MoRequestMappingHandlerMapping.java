package com.mozhengfly.boot.web.context;

import com.mozhengfly.boot.web.context.request.IRequestMappingAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * VersionRequestMapping
 *
 * @Description 自定义Version处理类
 * @Author mozhengfly
 * @Date 2019-12-13 14:34:26
 * @Version 1.0.0
 */
@Slf4j
public class MoRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private List<IRequestMappingAdapter> requestMappingAdapters = new ArrayList<>();

    @SuppressWarnings("NullableProblems")
    @Override
    public RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        AtomicReference<RequestMappingInfo> info = new AtomicReference<>(super.getMappingForMethod(method, handlerType));
        if (info.get() != null && CollectionUtils.isNotEmpty(requestMappingAdapters)) {
            requestMappingAdapters.forEach(requestMappingAdapter -> info.set(requestMappingAdapter.adapter(method, handlerType, info.get())));
        }
        return info.get();
    }

    public void addRequestMappingAdapter(IRequestMappingAdapter adapter) {
        if (!ObjectUtils.isEmpty(adapter)) {
            this.requestMappingAdapters.add(adapter);
        }
    }

    public void addAllRequestMappingAdapters(List<IRequestMappingAdapter> requestMappingAdapters) {
        if (!ObjectUtils.isEmpty(requestMappingAdapters)) {
            this.requestMappingAdapters.addAll(requestMappingAdapters);
        }
    }
}
