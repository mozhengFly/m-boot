package com.mozhengfly.boot.web.configuration;

import com.mozhengfly.boot.web.context.VersionRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * VersionWebMvcRegistrations
 *
 * @Description ApiVersionConfiguration
 * @Author mozhengfly
 * @Date 2019-12-13 14:38:32
 * @Version 1.0.0
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VersionWebMvcRegistrations implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new VersionRequestMappingHandlerMapping();
    }
}
