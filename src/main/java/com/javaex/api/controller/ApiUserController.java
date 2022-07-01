package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;

@Controller
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping(value="/api/user/check" , method= {RequestMethod.GET,RequestMethod.POST})
	public boolean check(@RequestBody String id) {
		System.out.println("api/userController > check");
		
		boolean check = userService.userCheck(id);
		System.out.println(check);
		
		return check;
	}
	
	
} 
