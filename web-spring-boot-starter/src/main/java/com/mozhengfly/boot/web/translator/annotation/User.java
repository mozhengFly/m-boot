/**
 * @projectName build
 * @package com.mozhengfly.boot.web.translate.annotation
 * @className com.mozhengfly.boot.web.translate.annotation.User
 * @copyright Copyright 2021 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.translator.annotation;

import java.lang.annotation.*;

/**
 * User
 *
 * @Description User
 * @Author wangchonglin
 * @Date 2021-06-06 15:00:02
 * @Version 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface User {
}
