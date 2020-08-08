package com.mozhengfly.boot.security.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * JwtUserInfo
 * @Description JwtUserInfo
 * @Author wangchonglin
 * @Date 2020-01-02 17:27:27
 * @Version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class JwtUserInfo {

    /**
     * 用户id
     */
    private String id;

    /**
     * 设备id
     */
    private String deviceId;
}
