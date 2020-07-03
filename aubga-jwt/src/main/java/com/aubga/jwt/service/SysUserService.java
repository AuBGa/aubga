package com.aubga.jwt.service;

import org.springframework.stereotype.Service;


public interface SysUserService {
	public boolean login(String username, String password);
}
