package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.dto.EmailDTO;
import com.docmall.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RestController // 해당 컨트롤러 클래스가 ajax용도로만 사용하고자 할때 적용
@RequestMapping("/email/*") // 현재는 jsp 사용 안할 예정
public class EmailController {
	
	private final EmailService emailService;
	
//	메일인증 코드 요청 주소
	@GetMapping("/authCode")
	public ResponseEntity<String> authSend(EmailDTO dto , HttpSession session){
		log.info("이메일 정보:" + dto);
		
		ResponseEntity<String> entity = null;
		
//		인증코드 랜덤생성(6자리)
		String authCode = "";
		for(int i=0; i<6; i++) {
			authCode += String.valueOf((int)(Math.random()*10));
		}
		log.info("인증코드: " + authCode );
		
//		사용자에게 메일로 발급해준 인증코드를 입력시 비교목적으로 세션형태로 미리저장
		session.setAttribute("authCode", authCode);
		
		try {
			emailService.sendMail(dto, authCode); // 메일 보내기
			entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200상태
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500에러 서버오류 
		}
		
		return entity;
	}
	
}
