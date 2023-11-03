package com.docmall.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

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
//	String uploadFolder: 서버측의 업로드 될 폴
//	String dateFolder: 이미지 파일을 저장할 날짜 폴더명 2023/11/03
//	MultipartFile uploadFile: 업로드 된 파일을 참조하는 객체
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
		
//		a57f7113-69fa-457e-aedd-d9adcc9aa79f)(uuid.toString()에 해당)_산.jpg(clientFileName에 해당)
		realUploadFileName = uuid.toString() + "_"  + clientFileName;
		
		try {
//			여기 file에는 C:/dev/devTools/upload "2023/11/02" 가 들어있음
//			realUploadFileName 여기에는 실제 업로드 파일명  위 주석과 이 주석을 합쳐서 중복을 방지한다고 보면됨
			File saveFile = new File(file , realUploadFileName);
//			파일업로드(파일복사)
//			해당 작업을 위해서 파일 작업들이 진행된것임
			uploadFile.transferTo(saveFile);
			
//			썸네일 이미지 작업
//			원본이미지 파일의 크기가 커서 브라우저에 리스트로 사용시 로딩되는 시간이 길어진다.
//			원본이미지를 파일크기와 해상도를 낮추어 작은형태의 이미지로 만드는 것. 
			if(checkImageType(saveFile)) { // 첨부된 파일이 이미지 일 경우일때
//				파일 출력 스트림 클래스
//				밑에 줄만 실행이 되면, 파일이 실행
				// 썸네일 라이브러리에서 제공하는 클래스 , pom.xml참고
				FileOutputStream thumbnail = new FileOutputStream(new File(file,"s_" + realUploadFileName));
				
				Thumbnailator.createThumbnail(uploadFile.getInputStream(), thumbnail, 100, 100);
				
				thumbnail.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace(); // 파일 업로드시 예외가 발생되면, 예외 정보를 출력
		}		
		
		return realUploadFileName; // 상품테이블에 상품 이미지명이 저장.
	}
	
//	자바스크립트로 업로드 되는 파일의 확장자를 이용하여, 이미지 파일만 파일첨부 가능하도록 작업할 수가 있다.
//	업로드 되는 파일구분(이미지파일 또는 일반 파일 구분)
	private static boolean checkImageType(File saveFile) {
		
		boolean isImageType = false; // 변수의 값이 true면 이미지파일, false일때 일반파일 구분작업
		
		try {
			String contentType = Files.probeContentType(saveFile.toPath());
//			contentType 변수의 값이 image문자열로 시작되는지 여부를 true / false값으로 리턴
			isImageType = contentType.startsWith("image");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isImageType;
	}
	
	
}
