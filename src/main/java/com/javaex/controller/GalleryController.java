package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	
	
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String galleryList(Model model, int no) {
		System.out.println("GalleryController > galleryList ");
		
		List<GalleryVo> gVo = galleryService.galleryList(no);
		
		model.addAttribute("gVo", gVo);
		
		return "/gallery/list";
	}
	
	@RequestMapping(value="/save", method= {RequestMethod.GET,RequestMethod.POST})
	public String gallerySave(@RequestParam("file") MultipartFile file, Model model,@ModelAttribute GalleryVo galleryVo) {
		System.out.println("GalleryController > gallerySave ");
		
		galleryService.gallerySave(file, galleryVo);
		
		
		return "redirect:/gallery/list";
	}
	

	
	
	
}
