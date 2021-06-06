package com.mozhengfly.boot.security.service;

import com.mozhengfly.boot.security.pojo.AuthorityUser;

/**
 * IUserService
 *
 * @Description 定义UserService的接口
 * @Author mozhengfly
 * @Date 2020-08-08 21:42:12
 * @Version V1.0.0
 */
public interface IUserService {

    /**
     * 根据username查询出user信息
     * @param username
     * @return
     */
    AuthorityUser getUserByUsername(String username);
}
