package com.mozhengfly.boot.test.controller;

import com.mozhengfly.boot.test.vo.User;
import com.mozhengfly.boot.web.annotation.ApiVersion;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity hello(@RequestBody User user) {
        return ResponseEntity.ok("hello " + user.getName());
    }

    @GetMapping("hi")
    public ResponseEntity hi() {
        return ResponseEntity.ok("mozhengfly");
    }
}