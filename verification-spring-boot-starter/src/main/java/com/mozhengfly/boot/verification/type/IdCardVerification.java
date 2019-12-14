package com.mozhengfly.boot.verification.type;

import com.mozhengfly.boot.verification.AbstractVerification;
import com.mozhengfly.boot.verification.VerificationFactory;
import com.mozhengfly.boot.verification.VerificationResult;
import com.mozhengfly.boot.verification.util.IdCardUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @Description 身份证校验
 * @Author mozhengfly
 * @Date 2019-07-01 23:28:53
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public class IdCardVerification extends AbstractVerification {

    @Override
    public VerificationResult verifyValue(Object value) {
        Optional<VerificationResult> result = Optional.ofNullable(value).map(idCard -> {
            if (idCard instanceof String) {
                if (StringUtils.isBlank((String)idCard) || IdCardUtil.isIdCard((String)idCard)) {
                    return VerificationFactory.createSuccessResult("身份证校验成功");
                }
            }
            return VerificationFactory.createFailResult("身份证校验失败");
        });
        // 为空跳过校验
        return result.orElseGet(() -> VerificationFactory.createSuccessResult());
    }
}
