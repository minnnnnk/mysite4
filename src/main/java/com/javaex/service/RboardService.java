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
	
	public List<RboardVo> rBoardList(){
		System.out.println("RboardService > list");
		List<RboardVo> rList =  rboardDao.rBoardList();

		for(int i = 0; i<rList.size(); i++) {
			for(int j= 0; j<rList.get(i).getDepth(); j++) {
				rList.get(i).setTitle("&emsp; " + rList.get(i).getTitle());
				System.out.println(rList.get(i).getTitle());
			}
		}
		
		
		return rList;
	}
	
	public int rBoardWrite(RboardVo rboardVo) {
		System.out.println("RboardService > rBoardWrite");
		
		int count =rboardDao.rBoardWrite(rboardVo);
		
		return count;
	}
	
	public RboardVo read(int no) {
		System.out.println("RboardService > getBoard");
		
		rboardDao.hit(no);
		
		RboardVo rVo = rboardDao.getBoard(no);
		
		return rVo;
	}
	
	public RboardVo getBoard(int no) {
		System.out.println("RboardService > getBoard");
		RboardVo rVo = rboardDao.getBoard(no);
		
		return rVo;
	}
	
	public int commentWrite(RboardVo rboardVo) {
		System.out.println("RboardService > commentWrite");
		int count = rboardDao.commentWrite(rboardVo);
		
		return count;
	}
	
	public int delete(int no) {
		System.out.println("RboardService > delete");
		
		int count = rboardDao.delete(no);
		
		return count;
	}
	
	public int modify(RboardVo rboardVo) {
		System.out.println("RboardService > modify");
		
		int count =rboardDao.modify(rboardVo);
		
		return count;
	}
}
