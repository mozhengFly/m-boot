package com.mozhengfly.boot.test.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description @TODO
 * @Author mozhengfly
 * @Date 2019-08-02 21:06:46
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
public class TaskTest2 {

    @Scheduled(cron = "0/2 * * * * *")
    public void execute() {
        log.info("执行 TaskTest2 {} ", System.currentTimeMillis());
    }
}
