package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>login");
		
		UserVo authUser = userSerivce.login(userVo);
		
		
		/* 세션에 저장 */
		if(authUser != null) { //로그인 성공
			session.setAttribute("authUser", authUser);
			
		return "redirect:/main";
		}else {
			System.out.println("로그인 실패");
		return "redirect:/user/loginForm?result=fail";
		}
		
	}
	
	//로그아웃
	@RequestMapping(value="/user/logout", method= {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController>logout");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	//회원수정
	@RequestMapping(value="/user/modifyForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("UserController>modifyForm");
		
		UserVo getUser = userSerivce.getUser(no);
		
		model.addAttribute("getUser", getUser);
		
		return "/user/modifyForm";
	}
	
	@RequestMapping(value="/user/modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("UserController> modify");
		
		userSerivce.userUpdate(userVo);
		System.out.println(userVo);
		return "redirect:/main";
	}
}
