/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.configuration
 * @className com.mozhengfly.boot.web.configuration.HttpConvertConfiguration
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.configuration;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mozhengfly.boot.web.translator.FastJsonAfterFilter;
import com.mozhengfly.boot.web.translator.TranslateProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * HttpConvertConfiguration
 *
 * @Description HttpConvertConfiguration
 * @Author wangchonglin
 * @Date 2021-06-06 13:43:35
 * @Version 1.0.0
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({TranslateProperties.class})
public class HttpConvertConfiguration implements WebMvcConfigurer {

    private final FastJsonAfterFilter fastJsonAfterFilter;

    @Override
    public void extendMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        converters.removeIf(converter -> converter instanceof FastJsonHttpMessageConverter);
        FastJsonHttpMessageConverter fastCoverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializeFilters(fastJsonAfterFilter);
        fastCoverter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, fastCoverter);
    }
}
