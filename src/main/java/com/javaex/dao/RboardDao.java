package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<RboardVo> rBoardList(){
		System.out.println("RboardDao > list");
		List<RboardVo> rList = sqlSession.selectList("rboard.rboardList");
		
		return rList;
	}
	
	public int rBoardWrite(RboardVo rboardVo) {
		System.out.println("RboardDao > rBoardWrite");
		int count = sqlSession.insert("rboard.rboardWrite", rboardVo);
		
		return count;
	}
	
	public RboardVo getBoard(int no) {
		System.out.println("RboardDao > getBoard");
		RboardVo rVo = sqlSession.selectOne("rboard.getRboard", no);
		
		return rVo;
	}
	
	public int commentWrite(RboardVo rboardVo) {
		System.out.println("RboardDao > commentWrite");
		int count =sqlSession.insert("rboard.commentWrite", rboardVo);
		
		return count;
	}
	
	public int hit(int no) {
		System.out.println("RboardDao > hit");
		int count =sqlSession.update("rboard.hitUpdate", no);
		return count;
	}
	
	public int delete(int no) {
		System.out.println("RboardDao > delete");
		
		int count = sqlSession.delete("rboard.rboardDelete", no);
	
		return count;
	}
	
	public int modify(RboardVo rboardVo) {
		System.out.println("RboardDao > modify");
		
		int count = sqlSession.update("rboard.rboardUpdata", rboardVo);
		
		
		return count;
	}
	
	public int orderNo(RboardVo rboardVo) {
		
		int count = sqlSession.update("rboard.orderNoUpdate", rboardVo);
		
		return count;
	}
}
