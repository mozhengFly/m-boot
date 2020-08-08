package com.mozhengfly.boot.security.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * JwtAuthorityVo
 * @Description JwtAuthorityVo
 * @Author wangchonglin
 * @Date 2020-01-02 18:15:18
 * @Version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class JwtAuthorityVo {

    private String accessToken;

    private String refreshToken;
}
