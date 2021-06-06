package com.mozhengfly.boot.security.pojo;

import com.mozhengfly.boot.security.service.IUser;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AuthorityUser
 * @Description 授权的用户
 * @Author wangchonglin
 * @Date 2020-01-02 18:06:42
 * @Version 1.0.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityUser implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private IUser user;

    /**
     * 角色集合
     */
    private Set<String> roles;

    /**
     * 权限集合
     */
    private Set<String> permissions;

    public Set<GrantedAuthority> generateGrantedAuthority() {
        Set<GrantedAuthority> set = new HashSet<>();
        if (!CollectionUtils.isEmpty(this.permissions)) {
            this.permissions.forEach(item -> {
                set.add(new SimpleGrantedAuthority(item));
            });
        }
        if (!CollectionUtils.isEmpty(this.roles)) {
            this.roles.forEach(item -> {
                set.add(new SimpleGrantedAuthority("ROLE_" + item));
            });
        }
        return set;
    }
}
