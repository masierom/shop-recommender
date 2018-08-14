package com.example.shoprecommender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopRecommenderApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testRecommended() throws Exception {
		RequestBuilder getRequest = MockMvcRequestBuilders.get("/api/reccomendations/{productId}/{userId}",
				"b99c88ce-c135-48ac-a756-a2fc3e5e4ab6","33")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8);
		
		mockMvc.perform(getRequest)
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Tomato Soup"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Smartphone Samsung"));
	}

}
