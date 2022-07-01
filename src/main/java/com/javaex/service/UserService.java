package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("UserService > join");
		
		int count = userDao.userJoin(userVo);
		
		System.out.println(userVo);
		
		return count;
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("UserService > login");
		UserVo uVo = userDao.loginUser(userVo);
		return uVo;
	}
	
	public UserVo getUser(int no) {
		System.out.println("UserService > getUser");
		
		UserVo uVo = userDao.getUser(no);
		
		return uVo;
	}
	
	public int userUpdate(UserVo userVo) {
		System.out.println("UserService > userUpdate");
		
		int count = userDao.userUpdate(userVo);
		System.out.println(count + "건 변경되었습니다");
		return count;
	}
	
	public boolean userCheck(String id){
		System.out.println("userService > userchekc");
		List<String> idList = userDao.userCheck();
		
		
		for(int i = 0; i<idList.size(); i++) {
			String uId= idList.get(i);
			if(id.equals(uId)) {
				return true;
			}
		}
		
		
		return false;
	}
}
