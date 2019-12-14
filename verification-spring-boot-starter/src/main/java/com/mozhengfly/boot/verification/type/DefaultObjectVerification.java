package com.mozhengfly.boot.verification.type;

import com.mozhengfly.boot.verification.AbstractVerification;
import com.mozhengfly.boot.verification.VerificationFactory;
import com.mozhengfly.boot.verification.VerificationResult;

/**
 * @Description 默认的对象校验类
 * @Author mozhengfly
 * @Date 2019-07-01 22:24:09
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public class DefaultObjectVerification extends AbstractVerification {

    @Override
    public VerificationResult verifyValue(Object value) {
        return VerificationFactory.createSuccessResult();
    }
}
