package com.mozhengfly.boot.security.service;

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
    IUser getUserByUsername(String username);
}
