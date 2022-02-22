package com.example.Test.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test.mapper.UserMapper;
import com.example.Test.vo.User;

@RestController
public class UserController {
	@Autowired
	UserMapper userMapper;

	@GetMapping("/userList")
	public List<User> selectUserList() throws ParseException {
		List<User> userList = userMapper.selectUserData();
		// System.out.println(userList + "ajax 호출");
		return userList;
	}
}
