package com.mozhengfly.boot.security.pojo;

import com.mozhengfly.boot.security.service.IUser;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * AuthorityUser
 * @Description 授权的用户
 * @Author wangchonglin
 * @Date 2020-01-02 18:06:42
 * @Version 1.0.0
 */
@Getter
@Setter
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
    private List<String> roles;

    /**
     * 权限集合
     */
    private List<String> permissions;
}
