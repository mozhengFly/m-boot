package com.mozhengfly.boot.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint
 *
 * @Description JwtAuthenticationEntryPoint
 * @Author mozhengfly
 * @Date 2020-01-02 23:27:13
 * @Version V1.0.0
 */
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error("token校验失败", e);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "没有凭证");
    }
}
