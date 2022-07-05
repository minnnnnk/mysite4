package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardCotroller > List");
		List<BoardVo> bList = boardService.getBoardList();
		System.out.println(bList);
		model.addAttribute("bList",bList);
		
		return "/board/list";
	}
	
	@RequestMapping(value="/list3", method= {RequestMethod.GET,RequestMethod.POST})
	public String list3(Model model, @RequestParam(value="keyword",required = false, defaultValue = "") String title) {
		System.out.println("BoardCotroller > List3");
		List<BoardVo> bList = boardService.List3(title);
		
		System.out.println(bList);
		
		model.addAttribute("bList",bList);
		
		return "/board/list";
	}
	
	//리스트일반
	@RequestMapping(value="/list4", method= {RequestMethod.GET,RequestMethod.POST})
	public String list4(Model model, @RequestParam(value="crtPage" ,required = false , defaultValue = "1") int crtPage) {
		System.out.println("BoardCotroller > List3");
		
		Map<String, Object> pMap = boardService.getBoardList4(crtPage);
		
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		
		return "/board/list";
	}
	//검색
	@RequestMapping(value="/search" ,method= {RequestMethod.GET,RequestMethod.POST})
	public String search(String title, Model model) {
		System.out.println("BoardController > search");
	
		
		List<BoardVo> bList = boardService.searchList(title);
		System.out.println(bList);
		
		model.addAttribute("bList", bList);
		
		
		return "/board/list";
	}
	
	//글쓰기폼
	@RequestMapping(value="/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardCotroller > writeForm");
		
		return "/board/writeForm";
	}
	//글쓰기
	@RequestMapping(value="/write" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > write");
		
		boardService.addBoard(boardVo);
		
		return "redirect:/board/list";
	}
	//글 불러오기
	@RequestMapping(value="/read",method= {RequestMethod.GET,RequestMethod.POST})
	public String read(@RequestParam("no") int no ,Model model) {
		System.out.println("BoardController > read");
		
		
		BoardVo bVo = boardService.read(no);
		
		System.out.println(bVo);
		model.addAttribute("bVo" , bVo);
		
		
		return "/board/read";
	}
	//수정폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no ,Model model) {
		System.out.println("BoardController > modifyForm");
		
		BoardVo bVo = boardService.getBoard(no);
		System.out.println(bVo);
		
		model.addAttribute("bVo", bVo);
		return "/board/modifyForm";
	}
	//수정
	@RequestMapping(value="/modify" ,method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > modify");
		boardService.boardModify(boardVo);
		System.out.println(boardVo);
		
		return "redirect:/board/list";
	}
	//삭제
	@RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no ) {
		System.out.println("BoardController > delete");
		boardService.delete(no);
		return "redirect:/board/list";
	}

} 
