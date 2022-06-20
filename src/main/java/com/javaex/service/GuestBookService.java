package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getGuestBookList() {
		
		System.out.println("guestBookService > List");
		
		List<GuestBookVo> gList = guestBookDao.getGuestList();
		
		return gList;
		
	}
	
	public int add(GuestBookVo guestbookVo) {
		System.out.println("guestBookService > add");
		System.out.println(guestbookVo);
		int count = guestBookDao.add(guestbookVo);
		System.out.println(count+"건 등록되었습니다");
		return count;
	}
	
	public int guestBookDelete(GuestBookVo guestBookVo) {
		System.out.println("guestBookService > delete");
		System.out.println(guestBookVo);
		int count = guestBookDao.guestBookDelete(guestBookVo);
		System.out.println(count+"건이 삭제되었습니다");
		return count;
	}
	
	public GuestBookVo getGuest(int no) {
		
		GuestBookVo gVo = guestBookDao.getGuest(no);
		
		System.out.println(gVo);
		return gVo;
	}
	
}
