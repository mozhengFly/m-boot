package com.mozhengfly.boot.validation.business.validator;


import com.mozhengfly.boot.validation.AbstractCustomValidator;
import com.mozhengfly.boot.validation.business.entity.Person;

/**
 * PersonCustomValidator
 *
 * @Description PersonCustomValidator
 * @Author wangchonglin
 * @Date 2020-01-07 15:26:45
 * @Version 1.0.0
 */
public class PersonCustomValidator extends AbstractCustomValidator<Person> {

    @Override
    public boolean isValid(Person person) {
        return true;
    }
}
