package com.aubga.jwt.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aubga.jwt.JWTApplicationTests;

public class SysUserServiceTest extends JWTApplicationTests{
	
	@Autowired
	SysUserService sysUserService;
	
	@Test
	public void login() {
		boolean login = sysUserService.login(username, password);
		Assert.assertTrue(login);
	}
	
}
