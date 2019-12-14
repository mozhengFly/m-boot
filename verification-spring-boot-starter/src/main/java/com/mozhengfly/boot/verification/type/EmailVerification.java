package com.mozhengfly.boot.verification.type;

import com.mozhengfly.boot.verification.AbstractVerification;
import com.mozhengfly.boot.verification.VerificationFactory;
import com.mozhengfly.boot.verification.VerificationResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @Description 邮件校验
 * @Author mozhengfly
 * @Date 2019-07-01 22:24:09
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
public class EmailVerification extends AbstractVerification {

    @Override
    public VerificationResult verifyValue(Object value) {
        Optional<VerificationResult> result = Optional.ofNullable(value).map(email -> {
            if (email instanceof String) {
                if (checkEmail((String)email)) {
                    return VerificationFactory.createSuccessResult("email校验成功");
                }
            }
            return VerificationFactory.createFailResult("邮件格式不规范");
        });
        // 为空跳过校验
        return result.orElseGet(() -> VerificationFactory.createSuccessResult());
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    private boolean checkEmail(String email) {
        // 为空不进行校验，默认为true，如果为空也需要进行校验，请配置require为true使用
        if (StringUtils.isBlank(email)) {
            return true;
        }
        String regex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return email.matches(regex);
    }
}
