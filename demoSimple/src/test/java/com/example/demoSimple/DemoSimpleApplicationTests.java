package com.example.demoSimple;

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
public class DemoSimpleApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
		String image = "{\"image\":\"test\"}";
		try {
			this.mvc.perform(post("http://localhost:8081/api/image").content(image)).andExpect(status().isOk())
					.andExpect(content().string(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

