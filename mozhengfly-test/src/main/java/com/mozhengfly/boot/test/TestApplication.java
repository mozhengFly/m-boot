package com.mozhengfly.boot.test;

import com.mozhengfly.boot.web.annotation.EnableApiVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TestApplication
 * @Author mozhengfly
 * @Date 2019-07-01 22:22:28
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@EnableApiVersion
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
