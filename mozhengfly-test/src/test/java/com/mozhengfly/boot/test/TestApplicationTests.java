package com.mozhengfly.boot.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    private int fixNum = 5;

    @Autowired
    private RedissonClient redisson;

    @Test
    public void contextLoads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(fixNum);
        ExecutorService exec = Executors.newFixedThreadPool(fixNum);
        for (int i = 0; i < fixNum; i++) {
            exec.submit(new TestLock("client-" + i, redisson, latch));
        }
//        latch.wait();
//        exec.shutdown();
        Thread.sleep(1000000000);
        log.info("所有任务执行完毕");
    }

    @Data
    @AllArgsConstructor
    static class TestLock implements Runnable {

        private String name;

        private RedissonClient redissonClient;

        private CountDownLatch countDownLatch;

        @Override
        public void run() {
            RLock lock = redissonClient.getLock("TestLock");
            try {
                log.info("----[{}]----等待获取锁", this.name);
                if (lock.tryLock(1000, 250, TimeUnit.MILLISECONDS)) {
                    try {
                        log.info("----[{}]----获取到锁", this.name);
                        Thread.sleep(200);
                        log.info("----[{}]----业务处理完毕", this.name);
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        log.error("业务处理失败", e);
                    } finally {
                        try {
                            lock.unlock();
                        } catch (Exception e) {
                            log.error("----[{}]----释放锁失败", this.name, e);
                        }
                        log.info("----[{}]----释放锁", this.name);
                    }
                } else {
                    log.info("lock.tryLock(), return false");
                }
            } catch (Exception e) {
                log.error("获取锁失败", e);
            }
        }
    }

}
