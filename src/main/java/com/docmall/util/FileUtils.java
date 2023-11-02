package com.docmall.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

//	파일 업로드 관련 필요한 메소드를 구성
public class FileUtils {
	
//	날짜 폴더 생성을 위한 날짜 정보
	public static String getDateFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM-dd");
		Date date = new Date();
		
		String str = sdf.format(date);
		
//		File.separator: 각 OS별로 경로 구분자를 반환해주는 기능
//		유닉스,맥,리눅스: "/"
//		윈도우 : "\" , "/"
		return str.replace("-", File.separator);
		
	}
	
//	서버에 파일을 업로드 하는 기능 및 실제 업로드 한 파일명 반환
	public static String uploadFile(String uploadFolder,String dateFolder ,MultipartFile uploadFile) {
		String realUploadFileName = "";
		
//		File 클래스: 파일과 폴더관련 작업하는 기능
		File file = new File(uploadFolder, dateFolder); // C:/dev/devTools/upload  "2023/11/02"
		
//		C:/dev/devTools/upload  "2023/11/02" 해당 폴더경로가 없으면 폴더명 생성
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		String clientFileName = uploadFile.getOriginalFilename();
		
//		파일명을 중복되지 않게 하기위해서 고유한 이름에 사용하는 UUID기능 사용
		UUID uuid = UUID.randomUUID();
		realUploadFileName = uuid.toString() + "_"  + clientFileName;
		
		try {
//			여기 file에는 C:/dev/devTools/upload "2023/11/02" 가 들어있음
//			realUploadFileName 여기에는 실제 업로드 파일명  위 주석과 이 주석을 합쳐서 중복을 방지한다고 보면됨
			File saveFile = new File(file , realUploadFileName);
//			파일업로드(파일복사)
//			해당 작업을 위해서 파일 작업들이 진행된것임
			uploadFile.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace(); // 파일 업로드시 예외가 발생되면, 예외 정보를 출력
		}
		
		
				
		
		return realUploadFileName; // 상품테이블에 상품 이미지명이 저장.
	}
	
	
}
