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
		System.out.println(userList + "ajax 호출");
		/*
		 * for(User i : userList) {
		 * 
		 * ex)"2020-10-01 10:00:00"형태의 i.getRegDate를 2020년10월1일 10시00분 형태로 보여주기 위해 데이터
		 * 포맷 i.getRegDate가 String형이기 때문에 먼저 Date형으로 변환(String -> Date) 후 데이터 포맷 지정(Date
		 * -> SimpleDateFormat) 원하는 포맷으로 변경 후 i.setRegDate를 이용하여 변경된 값으로 List의 값 변경
		 * 
		 * SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //원하는 데이터 포맷 지정 Date to =
		 * simpleDateFormat.parse(i.getRegDate()); System.out.println(to + "날짜 변환");
		 * simpleDateFormat = new SimpleDateFormat("yyyy년MM월d일 HH시mm분");
		 * System.out.println(simpleDateFormat.format(to));
		 * System.out.println(i.getRegDate() + "userList 출력");
		 * i.setRegDate(simpleDateFormat.format(to)); }
		 */
		return userList;
	}
}
