package com.mozhengfly.boot.validation;

import com.alibaba.fastjson.JSONObject;
import com.mozhengfly.boot.validation.business.entity.Person;
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

import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidationApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void no_first_name_and_no_last_name_should_be_successful() throws Exception {
        Person person = new Person();
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void no_first_name_but_has_last_name_should_be_failed() throws Exception {
        Person person = new Person();
        person.setLastName("chonglin");
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void error_id_card_should_be_failed() throws Exception {
        Person person = new Person();
        person.setIdCard("110101198409010099");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void id_is_null_when_update_should_be_failed() throws Exception {
        Person person = new Person();
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.put("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void id_is_not_null_when_update_should_be_successful() throws Exception {
        Person person = new Person();
        person.setId("123");
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.put("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void create_time_is_less_than_update_time_should_be_successful() throws Exception {
        Person person = new Person();
        person.setIdCard("11010119840901009X");
        person.setCreateTime(LocalDateTime.of(2018, 6, 12, 0, 0));
        person.setUpdateTime(LocalDateTime.of(2019, 6, 12, 0, 0));
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void create_time_is_more_than_update_time_should_be_failed() throws Exception {
        Person person = new Person();
        person.setIdCard("11010119840901009X");
        person.setCreateTime(LocalDateTime.of(2018, 6, 12, 0, 0));
        person.setUpdateTime(LocalDateTime.of(2017, 6, 12, 0, 0));
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void age_is_more_than_18_and_universityDiploma_is_null_should_be_failed() throws Exception {
        Person person = new Person();
        person.setAge(26);
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void age_is_less_than_18_and_universityDiploma_is_null_should_be_successful() throws Exception {
        Person person = new Person();
        person.setAge(8);
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void when_be_limit_login_and_password_is_null_should_be_failed() throws Exception {
        Person person = new Person();
        person.setLimitLogin(true);
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void when_be_limit_login_and_password_is_not_null_should_be_successful() throws Exception {
        Person person = new Person();
        person.setLimitLogin(true);
        person.setPassword("123");
        person.setIdCard("11010119840901009X");
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(person)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
