package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@RequestMapping(value="joinForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController>joinForm");
		return "/user/joinForm";
	}
	@RequestMapping(value="join", method = {RequestMethod.GET,RequestMethod.POST})
	public String join() {
		System.out.println("UserController>join");
		return "redirect:/user/joinOk";
	}
	@RequestMapping(value="loginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController>loginForm");
		return "/user/loginForm";
	}
	
	@RequestMapping(value="login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login() {
		System.out.println("UserController>login");
		return "";
	}
	
}
