package com.mozhengfly.boot.storage.configuration;

import com.mozhengfly.boot.storage.constants.StorageConst;
import com.mozhengfly.boot.storage.properties.AliyunProperties;
import com.mozhengfly.boot.storage.service.impl.AliyunStorageImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Storage自动配置类
 * @Author mozhengfly
 * @Date 2018-12-06 23:31:05
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Configuration
@ConditionalOnClass({AliyunStorageImpl.class})
@EnableConfigurationProperties({AliyunProperties.class})
public class StorageConfiguration {

    /**
     * 阿里云Service配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(AliyunStorageImpl.class)
    @ConditionalOnProperty(prefix = StorageConst.A_LI_YUN_PREFIX, value = "enable")
    public AliyunStorageImpl aliyunStorage() {
        return new AliyunStorageImpl();
    }

}
