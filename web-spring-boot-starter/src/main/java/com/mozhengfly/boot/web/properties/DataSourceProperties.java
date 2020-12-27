/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.properties
 * @className com.mozhengfly.boot.web.properties.DataSourceProperties
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * DataSourceProperties
 * @Description 数据源配置
 * @Author wangchonglin
 * @Date 2020-12-27 11:15:12
 * @Version 1.0.0
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {

    /**
     * IP端口
     */
    private String host;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
