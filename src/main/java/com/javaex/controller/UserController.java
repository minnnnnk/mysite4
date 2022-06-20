package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	
	//회원가입폼
	@RequestMapping(value="/user/joinForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController>joinForm");
		return "/user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/user/join", method = {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>join");
		
		userSerivce.join(userVo);
		
		return "redirect:/user/joinOk";
	}
	
	//회원가입후
	@RequestMapping(value="/user/joinOk", method = {RequestMethod.GET,RequestMethod.POST})
	public String joinOk() {
		System.out.println("UserController > joinOk");
		return "/user/joinOk";
	}
	//로그인폼
	@RequestMapping(value="/user/loginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController>loginForm");
		
		return "/user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/user/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login() {
		System.out.println("UserController>login");
		return "redirect:/mysite4/main";
	}
	
}
