/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.translate
 * @className com.mozhengfly.boot.web.translate.ITranslator
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator;

import java.lang.annotation.Annotation;

/**
 * ITranslator
 *
 * @Description 抽象类接口
 * @Author wangchonglin
 * @Date 2021-06-06 13:55:20
 * @Version 1.0.0
 */
public interface ITranslator<T extends Annotation> {

    String translate(TranslateParam translateParam);
}
