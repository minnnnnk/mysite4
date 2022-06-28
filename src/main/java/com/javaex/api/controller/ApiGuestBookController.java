package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestbookservice;
	
	@RequestMapping(value="/api/guestbook/addList",method= {RequestMethod.GET,RequestMethod.POST})
	public String addList() {
		System.out.println("apiGuestBookController > addList");
		
		
		return "/apiGuestbook/addList";
	}
	
	//방명록 리스트 데이타만 보내줘
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list",method= {RequestMethod.GET,RequestMethod.POST})
	public List<GuestBookVo> list() {
		System.out.println("apiGuestBookController > List");
		
		List<GuestBookVo> gList = guestbookservice.getGuestBookList();
		
		return gList;
	}
}
