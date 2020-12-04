package com.mozhengfly.boot.validation.validator;


import com.mozhengfly.boot.validation.annotation.StringField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * StringFieldValidator
 * @Description StringFieldValidator
 * @Author wangchonglin
 * @Date 2020-07-06 15:02:26
 * @Version 1.0.0
 */
public class StringFieldValidator implements ConstraintValidator<StringField, String> {

    private String[] in;

    @Override
    public void initialize(StringField stringField) {
        this.in = stringField.in();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (this.in.length > 0) {
            // 配置了才判断
            return Arrays.asList(this.in).contains(s);
        }
        return true;
    }
}
