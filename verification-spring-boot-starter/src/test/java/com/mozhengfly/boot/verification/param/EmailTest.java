package com.mozhengfly.boot.verification.param;

import com.mozhengfly.boot.verification.VerificationResult;
import com.mozhengfly.boot.verification.type.EmailVerification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmailTest {

    @Test
    public void right_email_should_be_successful() {
        // given
        EmailVerification emailVerification = new EmailVerification();
        String email = "mohzengfly@163.com";
        // when
        VerificationResult result = emailVerification.verifyValue(email);
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }

    @Test
    public void right_email_2_should_be_successful() {
        // given
        EmailVerification emailVerification = new EmailVerification();
        String email = "mohzengfly@163.com.cn";
        // when
        VerificationResult result = emailVerification.verifyValue(email);
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }

    @Test
    public void wrong_email_should_not_be_successful() {
        // given
        EmailVerification emailVerification = new EmailVerification();
        String email = "mohzengfly@163";
        // when
        VerificationResult result = emailVerification.verifyValue(email);
        // then
        Assert.assertEquals(result.isSuccess(), false);
    }

    @Test
    public void null_value_should_be_successful() {
        // given
        EmailVerification emailVerification = new EmailVerification();
        // when
        VerificationResult result = emailVerification.verifyValue(null);
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }

    @Test
    public void blank_value_should_be_successful() {
        // given
        EmailVerification emailVerification = new EmailVerification();
        // when
        VerificationResult result = emailVerification.verifyValue("");
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }
}
