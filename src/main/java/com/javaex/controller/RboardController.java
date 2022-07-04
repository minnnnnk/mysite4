package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {

	@Autowired
	private RboardService rboardService;
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("RboardController > list");
		List<RboardVo> rbList = rboardService.rBoardList();
		
		System.out.println(rbList);
		model.addAttribute("rbList", rbList);
		
		return "/rboard/list";
	}
	//읽기
	@RequestMapping(value="/read",method= {RequestMethod.GET,RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("RboardController > read");
		
		RboardVo rVo = rboardService.read(no);
		
		model.addAttribute("rVo", rVo);
		
		return "/rboard/read";
	}
	
	//등록폼
	@RequestMapping(value="/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("RboardController > wrtieForm");
		
		return "/rboard/writeForm";
	}
	
	//등록
	@RequestMapping(value="/write",method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute RboardVo rboardVo) {
		System.out.println("RboardController > write");
		rboardService.rBoardWrite(rboardVo);
		return "redirect:/rboard/list";
	}
	
	//댓글등록폼
	@RequestMapping(value="/commentForm" , method= {RequestMethod.GET,RequestMethod.POST})
	public String commentForm(Model model,@RequestParam("no") int no) {
		System.out.println("RboardController > commentForm");
		
		RboardVo rVo = rboardService.getBoard(no);
		
		model.addAttribute("rVo", rVo);
		
		return "/rboard/commentForm";
	}
	
	//댓글등록
	@RequestMapping(value="/comment" , method= {RequestMethod.GET,RequestMethod.POST})
	public String comment(@ModelAttribute RboardVo rboardVo) {
		System.out.println("RboardController > comment");
		System.out.println(rboardVo);
		
		rboardService.commentWrite(rboardVo);
		
		return "redirect:/rboard/list";
	}
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("RboardController > delete");
		
		rboardService.delete(no);
		
		return "redirect:/rboard/list";
	}
	//수정폼
	@RequestMapping(value="/modifyForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("RboardController > modifyForm");
		
		RboardVo rVo = rboardService.getBoard(no);
		model.addAttribute("rVo", rVo);
		
		return "/rboard/modifyForm";
	}
	
	//수정
	@RequestMapping(value="/modify",method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute RboardVo rboardVo) {
		System.out.println("RboardController > modify");
		System.out.println(rboardVo);
		rboardService.modify(rboardVo);
		
		return "redirect:/rboard/list";
	}
	
}
