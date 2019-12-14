package com.mozhengfly.boot.test.verification;

import com.mozhengfly.boot.verification.AbstractVerification;
import com.mozhengfly.boot.verification.VerificationFactory;
import com.mozhengfly.boot.verification.VerificationResult;

/**
 * @Description UserObjectVerification
 * @Author mozhengfly
 * @Date 2019-07-01 22:22:28
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public class UserObjectVerification extends AbstractVerification {

    @Override
    public VerificationResult verifyValue(Object value) {
        return VerificationFactory.createSuccessResult();
    }
}
