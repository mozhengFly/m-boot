/**
 * @projectName data-synchronization
 * @package com.mozhengfly.personal.web.practice
 * @className com.mozhengfly.personal.web.practice.RateLimiterIntercepter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * RateLimiterIntercepter
 * @Description 限流拦截器
 * @Author wangchonglin
 * @Date 2020-12-14 19:13:59
 * @Version 1.0.0
 */
@Slf4j
@Component
public class RateLimiterIntercepter implements HandlerInterceptor {

    @Setter
    private RateComponent rateComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("request.getPathInfo();{}", request.getRequestURI());
        log.info("request.getPathInfo();{}", request.getMethod());
        if (rateComponent.tryAcquire()) {
            return true;
        } else {
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write("被限流了 请求失败");
            pw.flush();
            pw.close();
            return false;
        }
    }
}
