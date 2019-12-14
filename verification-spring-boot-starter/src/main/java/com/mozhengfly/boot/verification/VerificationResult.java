package com.mozhengfly.boot.verification;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description VerificationResult
 * @Author mozhengfly
 * @Date 2019-07-01 23:20:43
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Data
@AllArgsConstructor
public class VerificationResult {

    private boolean success;

    private String message;
}
