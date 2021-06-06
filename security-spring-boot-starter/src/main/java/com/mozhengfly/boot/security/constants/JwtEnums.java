package com.mozhengfly.boot.security.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JwtEnums
 *
 * @Description JwtEnums
 * @Author wangchonglin
 * @Date 2020-01-02 17:50:04
 * @Version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum JwtEnums {

    /**
     * token 无效
     */
    INVALID(1, "无效的token"),
    /**
     * refresh token 无效
     */
    REFRESH_TOKEN_INVALID(2, "refresh token 无效"),
    /**
     * token过期
     */
    EXPIRED(3, "token过期"),
    /**
     * refresh token 过期
     */
    REFRESH_TOKEN_EXPIRED(4, "refresh token 过期"),
    /**
     * deviceId不匹配
     */
    DEVICE_ID_NOT_MATCH(5, "deviceId不匹配"),
    /**
     * 签名无效
     */
    SIGNATURE_INVALID(6, "签名无效"),
    /**
     * 用户名不存在
     */
    USER_NOT_EXIST(7, "用户不存在"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(8, "密码错误");

    /**
     * 码值
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;
}
