/**
 * @projectName data-synchronization
 * @package com.mozhengfly.personal.web.practice
 * @className com.mozhengfly.personal.web.practice.RateLimiterIntercepter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@AllArgsConstructor
public class RateLimiterIntercepter implements HandlerInterceptor {

    private IRateStrategy rateStrategy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (rateStrategy.tryAcquire(request.getRequestURI(), request.getMethod())) {
            return true;
        } else {
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.CONFLICT.value());
            PrintWriter pw = response.getWriter();
            pw.write("被限流了 请求失败");
            pw.flush();
            pw.close();
            return false;
        }
    }
}
