package com.mozhengfly.boot.web.configuration;

import com.mozhengfly.boot.web.context.MoWebMvcRegistrations;
import com.mozhengfly.boot.web.rate.IRequestMappingAdapter;
import com.mozhengfly.boot.web.rate.RateComponent;
import com.mozhengfly.boot.web.rate.RateRequestMappingAdapter;
import com.mozhengfly.boot.web.rate.VersionRequestMappingAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ApiVersionConfiguration
 * @Author mozhengfly
 * @Date 2019-12-15 13:14:41
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Configuration
public class ApiVersionConfiguration {

    @Bean
    @ConditionalOnMissingBean(MoWebMvcRegistrations.class)
    public MoWebMvcRegistrations moWebMvcRegistrations() {
        MoWebMvcRegistrations moWebMvcRegistrations = new MoWebMvcRegistrations();
        moWebMvcRegistrations.addRequestMappingAdapter(versionRequestMappingAdapter());
        moWebMvcRegistrations.addRequestMappingAdapter(rateRequestMappingAdapter());
        return moWebMvcRegistrations;
    }

    @Bean
    public RateComponent rateComponent() {
        return new RateComponent();
    }

    @Bean
    public IRequestMappingAdapter rateRequestMappingAdapter() {
        return new RateRequestMappingAdapter(rateComponent());
    }

    @Bean
    public IRequestMappingAdapter versionRequestMappingAdapter() {
        return new VersionRequestMappingAdapter();
    }
}
