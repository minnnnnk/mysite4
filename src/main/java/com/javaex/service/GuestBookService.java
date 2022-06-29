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
	
	
	//방명록 저장 (ajax)
	   public GuestBookVo addGuest(GuestBookVo gVo) {
	      System.out.println("GuestService>addGuest");
	      
	      guestBookDao.InsertGuest(gVo);
	      
	      int no = gVo.getNo();
	      
	      //방금 저장한 1개의 데이터를 가져온다
	      
	      GuestBookVo guestVo = guestBookDao.getGuest(no);
	      
	      return guestVo;
	   }
	   
	   //방명록 삭제 (ajax)
	   
	   public String remove(GuestBookVo gVo) {
		   
		   String state;
		   
		   int count = guestBookDao.remove(gVo);
		   
		   if(count>0) {
			   state = "success";
		   }else {
			   state ="fail";
		   }
		   System.out.println(state);
		   return state;
	   }
}
