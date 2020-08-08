package com.mozhengfly.boot.security.jwt;

import com.mozhengfly.boot.security.pojo.AuthorityUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * JwtContextHolder
 * @Description Jwt上下文
 * @Author wangchonglin
 * @Date 2020-01-02 17:09:13
 * @Version 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtContextHolder {

    private static final ThreadLocal<AuthorityUser> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }

    public static AuthorityUser getContext() {
        return CONTEXT_HOLDER.get();
    }

    public static void setContext(AuthorityUser user) {
        CONTEXT_HOLDER.set(user);
    }
}
