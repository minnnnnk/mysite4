package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//리스트
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService > List");
		
		List<BoardVo> bList = boardDao.getBoardList();
		
		return bList;
	}
	//글쓰기
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardService > boardWrite");
		int count = boardDao.boardWrite(boardVo);
		System.out.println(count+"건이 등록되었습니다");
		
		return count;
	}
	
	//한명 가져오기
	public BoardVo getBoard(int no) {
		System.out.println("BoardService > getRead");
		BoardVo bVo = boardDao.getBoard(no);
		return bVo;
	}
	//수정
	public int boardModify(BoardVo boardVo) {
		System.out.println("BoardService > boardModify");
		int count = boardDao.boardModify(boardVo);
		System.out.println(count+"건 변경되었습니다");
		
		return count;
	}
	//조회수
	public int hitUpdate(int no) {
		System.out.println("BoardService > hitUpdate");
		int count = boardDao.hitUpdate(no);
		System.out.println(count+"건 변경됨");
		
		return count;
	}
	//검색
	public List<BoardVo> searchList(String title) {
		System.out.println("BoardService > searchList");
		List<BoardVo> bList = boardDao.searchList(title);
		
		return bList;
	}
	//삭제
	public int delete(int no) {
		System.out.println("BoardService> delete");
		int count =boardDao.delete(no);
		System.out.println(count+"건 삭제되었습니다");
		return count;
	}
}
