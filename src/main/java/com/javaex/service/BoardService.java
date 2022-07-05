package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Object> getBoardList4(int crtPage) {
		System.out.println("BoardService > List");
		
		//페이지당 글갯수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		//시작글번호
		int startRNum = (crtPage-1)*listCnt + 1;
		
		//끝글번호
		int endRNum = (startRNum + listCnt) - 1;
		
		
		List<BoardVo> bList = boardDao.getBoardList4(startRNum,endRNum);
		
		////////////////////////////////////////////////////
		//페이징계산
		
		//전체글갯수
		int totalCnt = boardDao.selectTotalCnt();
		
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;

		//마지막 버튼 번호 
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount ;
		
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) +1;
		
		
		
		//다음 화살표 유무
		boolean next = false;
		if(listCnt*endPageBtnNo < totalCnt) {
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);  
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("bList", bList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		System.out.println(pMap);
		
		
		return pMap;
	}
	
	public int addBoard(BoardVo boardVo) {
		System.out.println("BoardService > addBoard");
		
		for(int i =1; i<=127; i++) {
			
			boardVo.setTitle(i+ "번쨰 게시글(제목)입니다");
			boardVo.setContent(i+"번째 게시글(컨텐트)00입니다");
			boardDao.boardWrite(boardVo);
		}
		
		return 1;
	}
	//글쓰기
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardService > boardWrite");
		int count = boardDao.boardWrite(boardVo);
		System.out.println(count+"건이 등록되었습니다");
		
		return count;
	}
	//리드
	public BoardVo read(int no) {
		
		boardDao.hitUpdate(no);
		
		BoardVo count = boardDao.getBoard(no);
		
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

	//검색
	public List<BoardVo> searchList(String title) {
		System.out.println("BoardService > searchList");
		
		if(title == null) {
			title = "";
		}
		
		title = "%" + title + "%";
		
		List<BoardVo> bList = boardDao.searchList(title);
		
		return bList;
	}
	//검색
	public List<BoardVo> List3(String title) {
		System.out.println("BoardService > List3");
		
		List<BoardVo> bList = boardDao.List3(title);
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
