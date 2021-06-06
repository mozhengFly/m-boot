/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.translator
 * @className com.mozhengfly.boot.web.translator.TranslateProperties
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TranslateProperties
 *
 * @Description 翻译的配置类
 * @Author wangchonglin
 * @Date 2021-06-06 16:28:13
 * @Version 1.0.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "mozhengfly.translate")
public class TranslateProperties {

    private String suffix = "TranslateText";
}
