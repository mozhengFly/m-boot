package com.mozhengfly.boot.security.jwt;

import com.mozhengfly.boot.security.constants.JwtConstants;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthorizationTokenFilter
 *
 * @Description JwtAuthorizationTokenFilter
 * @Author mozhengfly
 * @Date 2020-01-02 23:32:18
 * @Version V1.0.0
 */
@Setter
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private UserDetailsService jwtUserDetailsService;

    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(JwtConstants.AUTHORIZATION);
        final String deviceId = request.getHeader(JwtConstants.DEVICE_ID);
        if (StringUtils.isNotBlank(authorization) && StringUtils.isNotBlank(deviceId)) {
            String userId = jwtTokenProvider.getUserIdFromToken(authorization, deviceId);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = this.jwtUserDetailsService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
