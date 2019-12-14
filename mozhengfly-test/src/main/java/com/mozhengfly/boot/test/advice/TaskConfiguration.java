package com.mozhengfly.boot.test.advice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description @TODO
 * @Author mozhengfly
 * @Date 2019-08-02 21:13:02
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Configuration
@ConditionalOnClass({TaskTest1.class, TaskTest2.class})
@ConditionalOnProperty(prefix = "time.task", value = "open")
public class TaskConfiguration {

    @Bean
    @ConditionalOnMissingBean(TaskTest1.class)
    public TaskTest1 taskTest1() {
        return new TaskTest1();
    }

    @Bean
    @ConditionalOnMissingBean(TaskTest2.class)
    public TaskTest2 taskTest2() {
        return new TaskTest2();
    }
}
