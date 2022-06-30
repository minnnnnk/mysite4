package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int fileSave(FileVo fileVo) {
		
		int count = sqlSession.insert("file.fileInsert", fileVo);
		
		return count;
	}
}
