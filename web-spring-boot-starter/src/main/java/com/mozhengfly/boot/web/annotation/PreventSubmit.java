/**
 * @projectName build
 * @package com.mozhengfly.boot.web.annotation
 * @className com.mozhengfly.boot.web.annotation.PreventSubmit
 * @copyright Copyright 2020 Thuisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.annotation;


import java.lang.annotation.*;

/**
 * PreventSubmit
 *
 * @Description 防重复提交注解
 * @Author wangchonglin
 * @Date 2020-12-04 16:43:35
 * @Version 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreventSubmit {
}
