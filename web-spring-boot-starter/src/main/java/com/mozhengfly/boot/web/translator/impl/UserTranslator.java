/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.translate.impl
 * @className com.mozhengfly.boot.web.translate.impl.UserTranslator
 * @copyright Copyright 2021 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator.impl;


import com.mozhengfly.boot.web.translator.ITranslator;
import com.mozhengfly.boot.web.translator.TranslateParam;
import com.mozhengfly.boot.web.translator.annotation.User;
import org.springframework.stereotype.Component;

/**
 * UserTranslator
 *
 * @Description UserTranslator
 * @Author wangchonglin
 * @Date 2021-06-06 14:56:44
 * @Version 1.0.0
 */
@Component
public class UserTranslator implements ITranslator<User> {

    @Override
    public String translate(TranslateParam translateParam) {
        String s = translateParam.getFieldValue().toString();
        return s + "测试翻译值";
    }
}
