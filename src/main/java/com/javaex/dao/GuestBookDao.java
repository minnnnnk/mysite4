package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getGuestList() {
		
		System.out.println("guestBookDao > List");
		
		List<GuestBookVo> gList = sqlSession.selectList("guestbook.getGuestBookList"); /* 두개필없 */
		System.out.println(gList);
		return gList;
	}
	
	public int add(GuestBookVo guestbookVo) {
		System.out.println("guestBookDao > add");
		
		int count = sqlSession.insert("guestbook.guestBookInsert", guestbookVo);
		
		return count;
	}
	
	public int guestBookDelete(GuestBookVo guestbookVo) {
		System.out.println("guestBookDao > delete");
		
		int count = sqlSession.delete("guestbook.guestBookDelete", guestbookVo);
		
		return count;
	}
	
	public GuestBookVo getGuest(int no) {
		System.out.println("guestBookDao > getGuest");
		GuestBookVo gVo = sqlSession.selectOne("guestbook.getGuest" , no);
		return gVo;
	}
	
	//방명록 저장 ajax
    public int InsertGuest(GuestBookVo gVo) {
      System.out.println("GuestDao>InsertGuest");
      
      int count = sqlSession.insert("guestbook.insertSelectKey", gVo);
      return count;
   }
    
    //방명록 삭제 ajax
	public int remove(GuestBookVo guestbookVo) {
		System.out.println("guestBookDao > delete");
		
		int count = sqlSession.delete("guestbook.guestBookDelete", guestbookVo);
		
		return count;
	}
}
