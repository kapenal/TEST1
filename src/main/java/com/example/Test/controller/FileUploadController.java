package com.example.Test.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.Test.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	FileUploadService fileUploadService;
	
	@GetMapping("/FileUpload")
	public String getFileUpload() {	
		return "FileUpload";
	}
	
	@PostMapping("/FileUpload")
	public String postFileUpload(Model model,  MultipartFile uploadFile) {
		// System.out.println(uploadFile + "매개변수 확인");
		int fileUploadLine = 0;	// 입력하는 레코드의 수 기록하는 변수
        int uploadSuccessCount = 0;	// 입력 성공한 레코드의 수 기록하는 변수
        String fileHistory = ""; // 실패할 경우, 실패 레코드 내용 저장하는 변수
        List<String> failLine = new ArrayList<>(); // 실패한 라인, 실패한 텍스트를 저장할 List
		// 매개변수로 받아온 파일의 이름으로 File타입을 새로 생성
		File file = new File(uploadFile.getOriginalFilename());
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file); 
		    fos.write(uploadFile.getBytes());
		    fos.close();
		} catch (IOException e1) {
			System.out.println("예외 발생!");
			e1.printStackTrace();
		}
		//생성된 file을 스캐너로 파일 읽기
        Scanner scan = null;
		try {
			scan = new Scanner(file);
			int line = 0;
			// scanner를 이용하여 파일을 1줄씩 반복
			while(scan.hasNextLine()){
				line++;
				// 1줄씩 String형 userLine에 저장하여 uploadService호출
				 String userLine = scan.nextLine();
				 // System.out.println(userLine);
				 int resultCount = 0; // insert의 결과(return)값을 저장하는 변수
				 resultCount = fileUploadService.upload(userLine);
				/*insert의 결과(return)값이 0이면 insert의 실패한것이므로 실패 라인과 실패 텍스트를 fileHistory에 원하는String형으로 만든 후
				  failLine List에 저장*/
				 if(resultCount == 0) { 
					 fileHistory = "실패 라인 : "+line+"번째 줄 실패 텍스트 : "+userLine;
					 failLine.add(fileHistory);
				 }
				 uploadSuccessCount += resultCount;
				 fileUploadLine++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("예외 발생!");
			e.printStackTrace();
		}
        file.delete();
        scan.close();
        
        if(fileUploadLine == uploadSuccessCount) { // line수와 insert 성공한 수 가 같으면 모두 성공 페이지로
        	model.addAttribute("SuccessCount", uploadSuccessCount+"건 입력 성공");
        	return "ResultPage";
        } else { // 나머지는 모두 성공하지 못했기때문에 다른 페이지로
        	int failCount = 0; // 실패한 수를 저장하는 변수
        	failCount = fileUploadLine - uploadSuccessCount;
        	model.addAttribute("FailCount", "성공 "+uploadSuccessCount+"건, 실패 "+failCount+"건");
        	model.addAttribute("failLine", failLine);
        	return "FailPage";
        }
	}
}
