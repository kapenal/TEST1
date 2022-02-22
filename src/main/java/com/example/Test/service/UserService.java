package com.example.Test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Test.mapper.UserMapper;
import com.example.Test.vo.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<User> selectUserData() {
		List<User> userList = userMapper.selectUserData();
		return userList;
	}
}
