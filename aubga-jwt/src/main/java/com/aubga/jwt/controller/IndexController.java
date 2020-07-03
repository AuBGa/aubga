package com.aubga.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aubga.jwt.service.SysUserService;
import com.aubga.jwt.util.TokenUtil;

/**
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class IndexController {

	@Autowired
	SysUserService sysUserService;
	
	@PostMapping(value = "/login")
	public Map<String, Object> login(@RequestBody SysUser sysUser){
	    Map<String, Object> map = new HashMap<>();
	    String username = sysUser.getUsername();
	    System.out.println("username->"+username);
	    String password = sysUser.getPassword();
	    System.out.println("password->"+password);
	    if (sysUserService.login(username, password)){
	        String token = TokenUtil.sign(username,password);
	        if (token != null){
	            map.put("code", "10000");
	            map.put("message","认证成功");
	            map.put("token", token);
	            return map;
	        }
	    }
	    map.put("code", "00000");
	    map.put("message","认证失败");
	    return map;
	}

	
	
	@GetMapping(value = "/loginGet")
	public Map<String, Object> loginGet(@RequestParam String username,@RequestParam String password){
	    Map<String, Object> map = new HashMap<>();
	   
	    System.out.println("username->"+username);
	  
	    System.out.println("password->"+password);
	    if (sysUserService.login(username, password)){
	        String token = TokenUtil.sign(username,password);
	        if (token != null){
	            map.put("code", "10000");
	            map.put("message","认证成功");
	            map.put("token", token);
	            return map;
	        }
	    }
	    map.put("code", "00000");
	    map.put("message","认证失败");
	    return map;
	}
}