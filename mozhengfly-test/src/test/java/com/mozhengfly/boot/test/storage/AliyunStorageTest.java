package com.mozhengfly.boot.test.storage;

import com.mozhengfly.boot.storage.service.IStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description AliyunStorageTest
 * @Author mozhengfly
 * @Date 2019-06-29 22:48:26
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AliyunStorageTest {

    @Autowired
    private IStorage aliyunService;

    @Test
    public void testStorage() {
//        aliyunService.storeFile("H:\\timg.jpg");
    }
}
