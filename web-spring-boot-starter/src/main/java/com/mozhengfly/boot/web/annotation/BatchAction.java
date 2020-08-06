package com.mozhengfly.boot.web.annotation;

import java.lang.annotation.*;

/**
 * BatchAction
 *
 * @Description BatchAction
 * @Author wangchonglin
 * @Date 2020-08-05 16:51:40
 * @Version 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BatchAction {

    int index() default 0;

    int size() default 1000;
}
