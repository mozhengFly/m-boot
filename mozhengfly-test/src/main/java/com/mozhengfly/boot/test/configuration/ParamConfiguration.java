package com.mozhengfly.boot.test.configuration;

import com.mozhengfly.boot.verification.VerificationProperties;
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
public class ParamConfiguration {

    private static final String PARAM_POINT_CUT = "execution(* com.mozhengfly.boot.test.controller..*(..))";

    @Bean
    public VerificationProperties verificationProperties() {
        VerificationProperties properties = new VerificationProperties();
        properties.setPointCut(PARAM_POINT_CUT);
        return properties;
    }
}
