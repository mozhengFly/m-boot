package com.mozhengfly.boot.security.jwt;

import com.mozhengfly.boot.security.service.IUser;
import com.mozhengfly.boot.security.service.IUserService;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * JwtUserDetailsService
 *
 * @Description JwtUserDetailsService
 * @Author mozhengfly
 * @Date 2020-01-02 23:29:05
 * @Version V1.0.0
 */
@Slf4j
@Setter
@Accessors(chain = true)
public class JwtUserDetailsService implements UserDetailsService {

    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user by {}", username);
        IUser user = userService.getUserByUsername(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(user, authorityList);
    }
}
