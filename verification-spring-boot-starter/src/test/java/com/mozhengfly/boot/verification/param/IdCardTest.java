package com.mozhengfly.boot.verification.param;

import com.mozhengfly.boot.verification.VerificationResult;
import com.mozhengfly.boot.verification.type.IdCardVerification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class IdCardTest {

    @Test
    public void right_idCard_should_be_successful(){
        // given
        IdCardVerification idCardVerification = new IdCardVerification();
        String idCard = "110108198001010015";
        // when
        VerificationResult result = idCardVerification.verifyValue(idCard);
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }

    @Test
    public void wrong_idCard_should_not_be_successful() {
        // given
        IdCardVerification idCardVerification = new IdCardVerification();
        String idCard = "110108198001010014";
        // when
        VerificationResult result = idCardVerification.verifyValue(idCard);
        // then
        Assert.assertEquals(result.isSuccess(), false);
    }

    @Test
    public void null_value_should_be_successful() {
        // given
        IdCardVerification idCardVerification = new IdCardVerification();
        // when
        VerificationResult result = idCardVerification.verifyValue(null);
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }

    @Test
    public void blank_value_should_be_successful() {
        // given
        IdCardVerification idCardVerification = new IdCardVerification();
        // when
        VerificationResult result = idCardVerification.verifyValue("");
        // then
        Assert.assertEquals(result.isSuccess(), true);
    }
}
