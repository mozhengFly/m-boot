package com.mozhengfly.boot.web.context;

import com.mozhengfly.boot.web.context.request.IRequestMappingAdapter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/**
 * VersionWebMvcRegistrations
 *
 * @Description ApiVersionConfiguration
 * @Author mozhengfly
 * @Date 2019-12-13 14:38:32
 * @Version 1.0.0
 */
public class MoWebMvcRegistrations implements WebMvcRegistrations {

    private MoRequestMappingHandlerMapping moRequestMappingHandlerMapping = new MoRequestMappingHandlerMapping();

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return moRequestMappingHandlerMapping;
    }

    public void addRequestMappingAdapter(IRequestMappingAdapter adapter) {
        moRequestMappingHandlerMapping.addRequestMappingAdapter(adapter);
    }

    public void addAllRequestMappingAdapters(List<IRequestMappingAdapter> requestMappingAdapters) {
        moRequestMappingHandlerMapping.addAllRequestMappingAdapters(requestMappingAdapters);
    }
}
