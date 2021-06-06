/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.translate
 * @className com.mozhengfly.boot.web.translate.TranslateParam
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;

/**
 * TranslateParam
 *
 * @Description TranslateParam
 * @Author wangchonglin
 * @Date 2021-06-06 15:38:25
 * @Version 1.0.0
 */
@Setter
@Getter
@AllArgsConstructor
public class TranslateParam {

    /**
     * 真实的对象
     */
    private Object object;

    /**
     * 需要翻译的值
     */
    private Object fieldValue;

    /**
     * 翻译注解
     */
    private Annotation annotation;
}
