package com.mozhengfly.boot.test.controller;

import com.mozhengfly.boot.test.vo.UserInfo;
import com.mozhengfly.boot.web.annotation.ApiVersion;
import com.mozhengfly.boot.web.rate.RateLimit;
import com.mozhengfly.boot.web.translator.TranslateUtil;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description StorageController
 * @Author mozhengfly
 * @Date 2019-06-29 22:07:29
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@ApiVersion
@RestController
@Api(value = "StorageController", description = "存储管理")
public class StorageController {

    @ApiVersion(2)
    @PostMapping("/hello")
    public ResponseEntity hello(@RequestBody UserInfo user) {
        return ResponseEntity.ok("hello " + user.getName());
    }

    @RateLimit(0.5)
    @GetMapping("hi")
    public ResponseEntity hi() {
        return ResponseEntity.ok("mozhengfly");
    }

    @GetMapping("hi2")
    public ResponseEntity hi2() {
        return ResponseEntity.ok("mozhengfly");
    }

    @GetMapping("haha")
    public String test() {
        System.out.println(PatternMatchUtils.simpleMatch("/url/**", "/url/a"));
        System.out.println(PatternMatchUtils.simpleMatch("/url/{a}}", "/url/a"));
        System.out.println(PatternMatchUtils.simpleMatch("/url/*", "/url/a"));
        return "A";
    }

    @GetMapping(value = "u")
    public UserInfo get() {
        return UserInfo.builder().name("hah").build();
    }

    @GetMapping(value = "uu")
    public Map<String, String> getf() {
        return TranslateUtil.transformBean(UserInfo.builder().name("ha22h").build());
    }
}
