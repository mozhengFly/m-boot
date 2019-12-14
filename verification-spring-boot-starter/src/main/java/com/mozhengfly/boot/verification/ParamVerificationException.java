package com.mozhengfly.boot.verification;

/**
 * @Description ParamVerificationException
 * @Author mozhengfly
 * @Date 2019-07-01 22:10:40
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public class ParamVerificationException extends RuntimeException {

    public ParamVerificationException() {
        super();
    }

    public ParamVerificationException(String message) {
        super(message);
    }

    public ParamVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
