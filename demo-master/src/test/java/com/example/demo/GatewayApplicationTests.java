package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GatewayApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
        String image = "{\"image\":\"1234\"}";
        String expectedImage = "{\"image\":\"MTIzNA";
        try {
            this.mvc.perform(post("http://localhost:8083/demo/api/image").content(image)).andExpect(status().isOk())
                    .andExpect(content().string(expectedImage));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

