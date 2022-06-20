package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	public int userJoin(UserVo userVo) {
		System.out.println("UserDao > userJoin");
		System.out.println(userVo);
		
		int count = sqlsession.insert("userbook.userJoin", userVo);

		System.out.println(count+"건 등록되었습니다");
		
		return count;
	}
	
	
	
}
