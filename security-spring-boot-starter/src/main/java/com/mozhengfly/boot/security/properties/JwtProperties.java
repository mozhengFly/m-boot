package com.mozhengfly.boot.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JwtProperties
 * @Description JwtProperties
 * @Author wangchonglin
 * @Date 2020-01-02 15:37:33
 * @Version 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "mozhengfly.jwt")
public class JwtProperties {

    /**
     * token 秘钥 长度16位
     */
    private String secret = "monkey-123456789";

    /**
     * refresh token 秘钥 长度16位
     */
    private String refreshSecret = "small-monkey-123";

    /**
     * token 有效时间 单位毫秒
     */
    private Long expiration = 90000000L;

    /**
     * refresh token 有效期 单位毫秒
     */
    private Long refreshDuration = 9000000000L;

    /**
     * 忽略url
     */
    private String[] ignores;

}
