/**
 * @projectName build
 * @package com.mozhengfly.personal.web.practice
 * @className com.mozhengfly.personal.web.practice.RateLimit
 * @copyright Copyright 2020 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import java.lang.annotation.*;

/**
 * RateLimit
 *
 * @Description 限流注解
 * @Author wangchonglin
 * @Date 2020-12-14 19:39:26
 * @Version 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    double value() default 10D;
}
