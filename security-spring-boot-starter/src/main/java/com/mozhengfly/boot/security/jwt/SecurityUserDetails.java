package com.mozhengfly.boot.security.jwt;

import com.mozhengfly.boot.security.service.IUser;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * SecurityUserDetails
 *
 * @Description SecurityUserDetails
 * @Author mozhengfly
 * @Date 2020-01-02 23:30:13
 * @Version V1.0.0
 */
@Accessors(chain = true)
public class SecurityUserDetails implements UserDetails {

    private IUser user;

    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUserDetails(IUser user, Collection<? extends GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
