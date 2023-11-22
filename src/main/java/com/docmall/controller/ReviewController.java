package com.docmall.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.domain.MemberVO;
import com.docmall.domain.ReviewVO;
import com.docmall.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/user/review/*")
@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
//	@RequestBody: JSON 데이터를 VO객체로 변환해주는 기능, 반대의 기능은 @ResponseBody
	@PostMapping(
			value = "/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	
	public ResponseEntity<String> review_insert(@RequestBody ReviewVO vo,HttpSession session) throws Exception{
	
		
		
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		
		vo.setMbsp_id(mbsp_id);
		
		log.info(vo);
		
		ResponseEntity<String> entity = null;
		
//		DB저장
		reviewService.review_insert(vo);
		
		entity= new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@GetMapping("/list/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>> list(@PathVariable("pro_num")Integer pro_num, @PathVariable("page") Integer page) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		
		return entity;
	}
	
}
