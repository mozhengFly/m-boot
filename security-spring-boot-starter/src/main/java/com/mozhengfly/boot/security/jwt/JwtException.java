package com.mozhengfly.boot.security.jwt;

import com.mozhengfly.boot.security.constants.JwtEnums;
import lombok.Getter;

/**
 * JwtException
 * @Description JwtException
 * @Author wangchonglin
 * @Date 2020-01-02 17:48:02
 * @Version 1.0.0
 */
public class JwtException extends RuntimeException {

    private static final long serialVersionUID = -3972262280147177307L;

    @Getter
    protected int code;

    @Getter
    private String key;

    public JwtException(JwtEnums jwtEnums) {
        this(jwtEnums, null);
    }

    public JwtException(JwtEnums jwtEnums, Throwable cause) {
        super(jwtEnums.getMessage(), cause);
        this.code = jwtEnums.getCode();
    }

    public JwtException(String key, JwtEnums jwtEnums) {
        this(jwtEnums, null);
        this.key = key;
    }
}
