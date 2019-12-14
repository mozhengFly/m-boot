package com.mozhengfly.boot.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.mozhengfly.boot.test.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TestApplication
 * @Author mozhengfly
 * @Date 2019-07-01 22:22:28
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StorageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void right_user_data_should_be_successful() throws Exception{
        List<String> penList = new ArrayList<>();
        penList.add("pen");
        penList.add("pen2");
        User user = new User();
        user.setId("123");
        user.setName("mozhengfly");
        user.setBook(new String[]{"Chinese", "English", "Math"});
        user.setPen(penList);
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
