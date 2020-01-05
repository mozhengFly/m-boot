package com.mozhengfly.boot.tool.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * VerifyCodeUtil
 *
 * @Description 验证码工具类
 * @Author mozhengfly
 * @Date 2020-01-04 17:38:10
 * @Version V1.0.0
 */
public final class VerifyCodeUtil {

    private VerifyCodeUtil() {}

    private static final String VERIFY_CODES = "012345678ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String NUMBER_CODES = "0123456789";

    public static String generateFourCharactersCode() {
        return RandomStringUtils.random(4, VERIFY_CODES);
    }

    public static final String generateNumberCodes() {
        String codes = RandomStringUtils.random(9, NUMBER_CODES);
        if (StringUtils.startsWith(codes, "0")) {
            return generateNumberCodes();
        }
        return codes;
    }
}
