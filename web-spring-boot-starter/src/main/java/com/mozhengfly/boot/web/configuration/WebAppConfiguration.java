package com.mozhengfly.boot.web.configuration;

import com.mozhengfly.boot.web.converts.DateConverter;
import com.mozhengfly.boot.web.converts.LocalDateConverter;
import com.mozhengfly.boot.web.converts.LocalDateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * WebAppConfiguration
 * @Description WebAppConfiguration
 * @Author wangchonglin
 * @Date 2020-01-06 18:24:01
 * @Version 1.0.0
 */
@Configuration
public class WebAppConfiguration {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        assert initializer != null;
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new DateConverter());
            genericConversionService.addConverter(new LocalDateConverter());
            genericConversionService.addConverter(new LocalDateTimeConverter());
        }
    }

}
