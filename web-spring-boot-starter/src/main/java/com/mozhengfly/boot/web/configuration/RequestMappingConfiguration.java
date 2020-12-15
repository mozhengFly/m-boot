package com.mozhengfly.boot.web.configuration;

import com.mozhengfly.boot.web.context.MoWebMvcRegistrations;
import com.mozhengfly.boot.web.context.request.IRequestMappingAdapter;
import com.mozhengfly.boot.web.context.request.RateRequestMappingAdapter;
import com.mozhengfly.boot.web.context.request.VersionRequestMappingAdapter;
import com.mozhengfly.boot.web.rate.RateLimiterIntercepter;
import com.mozhengfly.boot.web.rate.RateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description ApiVersionConfiguration
 * @Author mozhengfly
 * @Date 2019-12-15 13:14:41
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Configuration
public class RequestMappingConfiguration implements WebMvcConfigurer {

    private List<IRequestMappingAdapter> requestMappingAdapters;

    @Autowired
    public void setRequestMappingAdapters(List<IRequestMappingAdapter> requestMappingAdapters) {
        this.requestMappingAdapters = requestMappingAdapters;
    }

    @Bean
    @ConditionalOnMissingBean(MoWebMvcRegistrations.class)
    public MoWebMvcRegistrations moWebMvcRegistrations() {
        MoWebMvcRegistrations moWebMvcRegistrations = new MoWebMvcRegistrations();
        moWebMvcRegistrations.addAllRequestMappingAdapters(requestMappingAdapters);
        return moWebMvcRegistrations;
    }

    @Bean
    public RateManager rateManager() {
        return new RateManager();
    }

    @Order(1)
    @Bean
    public IRequestMappingAdapter versionRequestMappingAdapter() {
        return new VersionRequestMappingAdapter();
    }

    @Order(2)
    @Bean
    public IRequestMappingAdapter rateRequestMappingAdapter() {
        return new RateRequestMappingAdapter(rateManager());
    }

    @Bean
    public RateLimiterIntercepter rateLimiterIntercepter() {
        return new RateLimiterIntercepter(rateManager());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimiterIntercepter()).addPathPatterns("/api/**");
    }
}
