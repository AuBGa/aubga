package com.aubga.jwt.controller;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.aubga.jwt.JWTApplicationTests;

public class IndexControllerTest extends JWTApplicationTests{
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	private MockHttpSession session;
	
	@Before
	public void setupMokcMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void login() throws Exception {
		String sysUser = "{\"username\":\"admin\",\"password\":\"admin\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(sysUser.getBytes())
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	
	@Test
	public void loginGet() throws Exception {
		String sysUser = "{\"username\":\"admin\",\"password\":\"admin\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/loginGet")
				.characterEncoding("utf-8")
			.param("username", "admin")
			.param("password", "admin")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
	}
}
