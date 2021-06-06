package com.mozhengfly.boot.security.configuration;

import com.mozhengfly.boot.security.jwt.JwtAuthenticationEntryPoint;
import com.mozhengfly.boot.security.jwt.JwtAuthorizationTokenFilter;
import com.mozhengfly.boot.security.jwt.JwtTokenProvider;
import com.mozhengfly.boot.security.jwt.JwtUserDetailsService;
import com.mozhengfly.boot.security.properties.JwtProperties;
import com.mozhengfly.boot.security.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * SecurityConfiguration
 *
 * @Description SecurityConfiguration
 * @Author mozhengfly
 * @Date 2020-01-02 23:24:41
 * @Version V1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties({JwtProperties.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private IUserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                // ignores
                .antMatchers(jwtProperties.getIgnores()).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                // 剩下所有的验证都需要验证
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                // 禁用 Spring Security 自带的跨域处理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.addFilterBefore(jwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService().setUserService(userService);
    }

    @Bean
    public OncePerRequestFilter jwtAuthorizationTokenFilter() {
        JwtAuthorizationTokenFilter tokenFilter = new JwtAuthorizationTokenFilter();
        tokenFilter.setJwtUserDetailsService(jwtUserDetailsService());
        tokenFilter.setJwtTokenProvider(jwtTokenProvider());
        return tokenFilter;
    }

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        jwtTokenProvider.setJwtProperties(jwtProperties);
        return jwtTokenProvider;
    }
}
