package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
public class ApiGalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@ResponseBody
	@RequestMapping(value="/api/gallery/read2",method= {RequestMethod.GET,RequestMethod.POST})
	public GalleryVo read2(@RequestBody String saveName) {
		System.out.println("APiGalleryController > read2 ");
		
		GalleryVo gVo = galleryService.getImage(saveName);
		
		
		System.out.println(gVo);
		
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/gallery/delete",method= {RequestMethod.GET,RequestMethod.POST})
	public int delete(@RequestBody int no) {
		
		galleryService.delete(no);
		
		
		return no;
	}
	
}	
