package com.mozhengfly.boot.security.jwt;

import com.mozhengfly.boot.security.pojo.AuthorityUser;
import com.mozhengfly.boot.security.service.IUserService;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        AuthorityUser user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new SecurityUserDetails(user.getUser(), user.generateGrantedAuthority());
    }
}
