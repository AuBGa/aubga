package com.aubga.jwt.service;

import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Override
	public boolean login(String username, String password) {
		if("admin".equals(username) && "admin".equals(password)) {
			return true;
		}
		return false;
	}

}
