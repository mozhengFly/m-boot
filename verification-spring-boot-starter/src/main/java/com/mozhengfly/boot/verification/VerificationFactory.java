package com.mozhengfly.boot.verification;

/**
 * @Description VerificationFactory
 * @Author mozhengfly
 * @Date 2019-07-01 23:22:00
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public final class VerificationFactory {

    private VerificationFactory() {}

    /**
     * 验证失败
     * @param message
     * @return
     */
    public static VerificationResult createFailResult(String message) {
        return new VerificationResult(false, message);
    }

    /**
     * 验证成功
     * @param message
     * @return
     */
    public static VerificationResult createSuccessResult(String message) {
        return new VerificationResult(true, message);
    }

    /**
     * 验证成功
     * @return
     */
    public static VerificationResult createSuccessResult() {
        return new VerificationResult(true, null);
    }
}
