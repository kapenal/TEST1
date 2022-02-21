package com.example.Test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Test.vo.User;

@Mapper
public interface UserMapper {
	List<User> selectUserData();
}
