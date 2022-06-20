package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping(value="guestBook/List", method = {RequestMethod.GET,RequestMethod.POST})
	public String guestBookList(Model model) {
		System.out.println("GuestBookController > List");
		
		List<GuestBookVo> gList = guestBookService.getGuestBookList();
		
		System.out.println(gList);
		
		model.addAttribute("gList",gList);
		
		return "/guestbook/addList";
	}
	@RequestMapping(value="guestBook/add",method = {RequestMethod.GET,RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController > add");
		
		guestBookService.add(guestBookVo);
		
		return "redirect:/guestBook/List";
	}
	@RequestMapping(value="guestBook/DeleteForm" , method= {RequestMethod.GET, RequestMethod.POST})
	public String guestBookDelete(@RequestParam("no") int no , Model model) {
		System.out.println("guestBookController > deleteForm");
		
		GuestBookVo gVo = guestBookService.getGuest(no);
		
		model.addAttribute("gVo", gVo);
		
		return "/guestbook/deleteForm";
	}
	@RequestMapping(value="guestBook/Delete" , method= {RequestMethod.GET,RequestMethod.POST})
	public String guestBookDeleteForm(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("guestBookController > delete");
		
		guestBookService.guestBookDelete(guestBookVo);
		
		return "redirect:/guestBook/List";
	}
	
	
}
