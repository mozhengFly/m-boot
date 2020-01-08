package com.mozhengfly.boot.validation.business.contoller;

import com.mozhengfly.boot.validation.business.entity.Person;
import com.mozhengfly.boot.validation.groups.InsertAction;
import com.mozhengfly.boot.validation.groups.UpdateAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * PersonController
 * @Description PersonController
 * @Author wangchonglin
 * @Date 2020-01-07 13:41:00
 * @Version 1.0.0
 */
@Slf4j
@RequestMapping("person")
@RestController
public class PersonController {

    @PostMapping
    public void savePerson(@Validated(InsertAction.class) @RequestBody Person person) {
        log.info("save person params : {}", person);
    }

    @PutMapping
    public void updatePerson(@Validated(UpdateAction.class) @RequestBody Person person) {
        log.info("update person params : {}", person);
    }
}
