package com.mozhengfly.boot.verification;

/**
 * @Description IVerification
 * @Author mozhengfly
 * @Date 2019-07-01 21:50:50
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public interface IVerification {

    /**
     * 校验数据
     * @param value
     * @return
     */
    VerificationResult verifyValue(Object value);
}
