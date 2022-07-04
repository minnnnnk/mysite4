package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {

	@Autowired
	private RboardDao rboardDao;
	
	//리스트
	public List<RboardVo> rBoardList(){
		System.out.println("RboardService > list");
		List<RboardVo> rList =  rboardDao.rBoardList();
		 
		//띄어쓰기 해주기
		for(int i = 0; i<rList.size(); i++) {
			for(int j= 0; j<rList.get(i).getDepth(); j++) {
				rList.get(i).setTitle("&emsp; " + rList.get(i).getTitle());
				System.out.println(rList.get(i).getTitle());
			}
		}
		
		return rList;
	}
	
	//등록
	public int rBoardWrite(RboardVo rboardVo) {
		System.out.println("RboardService > rBoardWrite");
		
		int count  = rboardDao.rBoardWrite(rboardVo);
		
		return count;
	}
	//읽기 + hit 업데이트
	public RboardVo read(int no) {
		System.out.println("RboardService > getBoard");
		
		rboardDao.hit(no);
		
		RboardVo rVo = rboardDao.getBoard(no);
		
		return rVo;
	}
	
	//정보가져오기
	public RboardVo getBoard(int no) {
		System.out.println("RboardService > getBoard");
		RboardVo rVo = rboardDao.getBoard(no);
		
		return rVo;
	}
	
	//댓글등록
	public int commentWrite(RboardVo rboardVo) {
		System.out.println("RboardService > commentWrite");
		
		rboardDao.commentWrite(rboardVo);
		
		int count = rboardDao.orderNo(rboardVo);
		
		return count;
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("RboardService > delete");
		
		int count = rboardDao.delete(no);
		
		return count;
	}
	
	//수정
	public int modify(RboardVo rboardVo) {
		System.out.println("RboardService > modify");
		
		int count =rboardDao.modify(rboardVo);
		
		return count;
	}
}
