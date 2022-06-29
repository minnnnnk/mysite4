package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestbookservice;
	
	//등록폼+리스트(첫페이지)
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
	
	//방명록 저장
	@ResponseBody
	@RequestMapping(value="/api/guestbook/add",method= {RequestMethod.GET,RequestMethod.POST})
	public GuestBookVo add(@ModelAttribute GuestBookVo guestbookVo) {
		System.out.println("apiGuestBookController > add");
		
		GuestBookVo gVo = guestbookservice.addGuest(guestbookVo);
		System.out.println(gVo);
		
		return gVo;
	}
	
	//삭제폼
	@RequestMapping(value="/api/guestbook/deleteForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		System.out.println("apiGuestBookController > deleteForm");
		
		return "apiGuestbook/deleteForm";
	}
	
	//삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbook/remove",method= {RequestMethod.GET,RequestMethod.POST})
	public String remove(@ModelAttribute GuestBookVo guestbookVo) {
		System.out.println(guestbookVo);
		
		String state = guestbookservice.remove(guestbookVo);
		
		return state;
	}
}
