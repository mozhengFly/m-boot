package com.mozhengfly.boot.web.configuration;

import com.mozhengfly.boot.web.context.VersionWebMvcRegistrations;
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
    @ConditionalOnMissingBean(VersionWebMvcRegistrations.class)
    public VersionWebMvcRegistrations versionWebMvcRegistrations() {
        return new VersionWebMvcRegistrations();
    }
}
