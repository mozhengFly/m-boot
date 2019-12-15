package com.mozhengfly.boot.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description SwaggerConfiguration
 * @Author mozhengfly
 * @Date 2019-06-29 23:43:35
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mozhengfly.boot.test.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("RestAPI Docs")
                .termsOfServiceUrl("http://www.github.com/kongchen/swagger-maven-plugin")
                .build();

    }
}
