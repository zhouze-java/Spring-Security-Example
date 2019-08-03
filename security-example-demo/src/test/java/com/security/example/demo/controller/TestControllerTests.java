package com.security.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 周泽
 * @date Create in 22:37 2019/8/2
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    /**
     * 单元测试执行之前执行
     */
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(get("/test/list").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void whenGetByIdSuccess() throws Exception {
        mockMvc.perform(get("/test/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInsertSuccess() throws Exception {
        String content = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"zhou\",\n" +
                "    \"sex\": 1,\n" +
                "    \"password\": \"123456\",\n" +
                "    \"phoneNo\": \"13211112222\"\n" +
                "}";

        String result = mockMvc.perform(post("/test").content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        log.info("结果:{}", result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\n" +
                "    \"name\": \"zhou\",\n" +
                "    \"sex\": 1,\n" +
                "    \"password\": \"123456\"\n" +
                "}";

        mockMvc.perform(put("/test/1").content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteSuccess() throws Exception {

        mockMvc.perform(delete("/test/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
