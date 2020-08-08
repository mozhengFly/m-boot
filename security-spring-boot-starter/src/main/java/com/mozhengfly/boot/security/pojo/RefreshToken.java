package com.mozhengfly.boot.security.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * RefreshToken
 * @Description 刷新Token
 * @Author wangchonglin
 * @Date 2020-01-02 17:07:12
 * @Version 1.0.0
 */
@Getter
@Setter
public class RefreshToken {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 过期时间
     */
    private Instant expiryTime;
}
