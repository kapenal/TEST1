package com.example.Test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.Test.vo.User;

@Mapper
public interface FileUploadMapper {
	int fileUpload(User user);
}
