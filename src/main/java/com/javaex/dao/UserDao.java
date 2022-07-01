package com.javaex.dao;

import java.util.List;

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
		
		int count = sqlsession.insert("user.userJoin", userVo);

		System.out.println(count+"건 등록되었습니다");
		
		return count;
	}
	
	public UserVo loginUser(UserVo userVo) {
		System.out.println("UserDao > loginUser");
		
		UserVo uVo = sqlsession.selectOne("user.loginUser" , userVo);
		
		return uVo;
	}
	
	public UserVo getUser(int no) {
		System.out.println("UserDao > getUser");
		
		UserVo uVo = sqlsession.selectOne("user.getUser", no);
		
		return uVo;
	}
	
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao > userUpdate");
		
		int count = sqlsession.update("user.userUpdate", userVo);
		
		return count;
	}
	
	public List<String> userCheck(){
		System.out.println("UserDao > userCheck");
		List<String> userId =sqlsession.selectList("user.userIdCheck");
		
		return userId;
	}
	
}
