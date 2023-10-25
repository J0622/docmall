package com.docmall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/join")
	public void join() {
		log.info("called... join");
		
	}
	
//	아이디 중복체크 기능
//	ajax문법으로 호출(비동기방식 처리)
	@GetMapping("/idCheck")
//	ajax요청이 왔을때: ResponseEntity<String>
//	결과 데이터와 HTTP상태코드를 직접 제어하는 구문: ResponseEntity<String>
//	ajax기능과 함께 사용
//	3가지 구성요소 : HttpStatus, HttpHeaders, HttpBody
	public ResponseEntity<String> idCheck(String mbsp_id){
		log.info("아이디: " + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
//		서비스 메소드 호출작업
		String idUse = "";
		if(memberService.idCheck(mbsp_id) != null) {
			idUse = "no"; // 아이디가 존재하여 불가능
		}else {
			idUse = "yes"; //아이디가 존재하지 않아 사용가능
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
}
