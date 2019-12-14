package com.mozhengfly.boot.verification.configuration;

import com.mozhengfly.boot.verification.VerificationProperties;
import com.mozhengfly.boot.verification.aspectj.ParamMethodBeforeAdvice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ParamConfiguration
 * @Author mozhengfly
 * @Date 2019-07-01 21:57:39
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Configuration
@ConditionalOnBean(VerificationProperties.class)
public class ParamConfiguration {

    @Autowired(required = false)
    private VerificationProperties properties;

    @Bean
    public Advisor beforeAdviceAdvisor() {
        if (StringUtils.isBlank(properties.getPointCut())) {
            throw new RuntimeException("没有参数校验切面的配置，请配置pointCut");
        }
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(properties.getPointCut());
        return new DefaultPointcutAdvisor(pointcut, paramMethodBeforeAdvice());
    }

    @Bean
    public ParamMethodBeforeAdvice paramMethodBeforeAdvice() {
        return new ParamMethodBeforeAdvice();
    }
}
