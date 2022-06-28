package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<BoardVo> getBoardList() {
		System.out.println("BoardDao > List");
		
		List<BoardVo> bList = sqlSession.selectList("board.BoardList");
		
		return bList;
		
	}
	//글쓰기
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardDao > boardWrite");
		int count = sqlSession.insert("board.BoardWrite", boardVo);
		
		
		return count;
	}
	//한명
	public BoardVo getBoard(int no) {
		System.out.println("BoardDao > getRead");
		BoardVo bVo = sqlSession.selectOne("board.getBoard", no);
		
		return bVo;
	}
	//수정
	public int boardModify(BoardVo boardVo) {
		System.out.println("boardDao > boardModify");
		int count = sqlSession.update("board.boardModify", boardVo);
		
		return count;
	}
	//조회수
	public int hitUpdate(int no) {
		System.out.println("boardDao > hitUpdate");
		int count = sqlSession.update("board.hitUpdate", no);
		
		return count;
	}
	//검색
	public List<BoardVo> searchList(String title){
		System.out.println("boardDao > searchList");
		List<BoardVo> bList = sqlSession.selectList("board.boardSearch" , title);
		
		return bList;
	}
	//삭제
	public int delete(int no) {
		System.out.println("boardDao > delete");
		int count = sqlSession.delete("board.boardDelete", no);
		
		return count;
	}
	
	public List<BoardVo> List3(String title){
		System.out.println("boardDao > List3");
		List<BoardVo> bList = sqlSession.selectList("board.selectList3", title);
		
		return bList;
	}
}
