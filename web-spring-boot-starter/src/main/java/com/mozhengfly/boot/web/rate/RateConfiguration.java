/**
 * @projectName data-synchronization
 * @package com.mozhengfly.personal.web.practice
 * @className com.mozhengfly.personal.web.practice.RateConfiguration
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * RateConfiguration
 *
 * @Description RateConfiguration
 * @Author wangchonglin
 * @Date 2020-12-14 19:20:46
 * @Version 1.0.0
 */
@Configuration
public class RateConfiguration implements WebMvcConfigurer {

    @Autowired
    private RateComponent rateComponent;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        RateLimiterIntercepter rateLimiterIntercepter = new RateLimiterIntercepter();
        rateLimiterIntercepter.setRateComponent(rateComponent);
        InterceptorRegistration registration = registry.addInterceptor(rateLimiterIntercepter);
        registration.addPathPatterns("/**");
    }

}
