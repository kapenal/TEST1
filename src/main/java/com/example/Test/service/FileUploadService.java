package com.example.Test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Test.mapper.FileUploadMapper;
import com.example.Test.vo.User;

@Service
public class FileUploadService {
	@Autowired
	FileUploadMapper fileUploadMapper;
	
	public int upload(String userLine) {
		// System.out.println(userLine + "매개변수 확인");
		String[] array = userLine.split("/"); // 매개변수를 "/"를 기준으로 구분하여 배열로 저장
		System.out.println(array.length); // 배열의 길이
		int uploadSuccessCount = 0;
		
		// if문을 사용하여 매개변수의 길이가 6이 아니면 DB의 저장이 안되도록 함
		// 공백도 길이의 포함되기 때문에 컬럼에 공백값이 있어도 실행 가능
		try {
			if(array.length == 6) {
				User user = new User();
				System.out.println("예외처리 실행 안함");
				user.setId(array[0]);
				user.setPwd(array[1]);
				user.setName(array[2]);
				user.setLevel(array[3].charAt(0)); // array를 이용시 char형으로 저장이 안되서 chartAt(0)을 사용하여 배열의 첫글자만 따로 추출해서 저장
				user.setDesc(array[4]);
				user.setRegDate(array[5]);
				// System.out.println(user.toString() + "toString실행");
				uploadSuccessCount = fileUploadMapper.fileUpload(user); // USER 객체를 사용하여 fileUploadMapper 호출
				System.out.println(uploadSuccessCount + "insert 결과값");
			} else { // array.length != 6 이면 DB에 저장이 안되도록 함
				System.out.println("예외 처리 발생!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadSuccessCount;
	}
}