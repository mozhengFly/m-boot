/**
 * @projectName validation
 * @package com.thunisoft.imp.validation.annotation
 * @className com.thunisoft.imp.validation.annotation.EnableValidation
 * @copyright Copyright 2020 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.validation.annotation;

import com.mozhengfly.boot.validation.ValidationConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableValidation
 *
 * @Description 自动验证
 * @Author wangchonglin
 * @Date 2020-07-06 15:55:39
 * @Version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ValidationConfiguration.class)
@Documented
public @interface EnableValidation {
}
