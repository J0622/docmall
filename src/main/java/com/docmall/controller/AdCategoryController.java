package com.docmall.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.CategoryVO;
import com.docmall.service.AdCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@RequestMapping("/admin/category/*")
@Log4j
@RequiredArgsConstructor
@Controller		// ajax 호출 또는 일반호출이 함께 사용되는 경우 해당 어노테이션 사용
//@RestController(@Controller + @ResponseBody) : 모든 매핑주소가 ajax 호출로 사용하는 경우 해당 어노테이션 사용 
public class AdCategoryController {
	private final AdCategoryService adCategoryService;
	
	// 1차 카테고리 선택에 따른 2차 카테고리 정보를 클라이언트에 제공
//	RestFull 개발론 : 기존 쿼리 스트링 형식(a & b & c)이 아닌  a/b/c 이렇게   
//	주소의 값의 일부분을 값으로 사용하고자 할경우 {} 중괄호 사용
	@ResponseBody
	@GetMapping("/secondCategory/{cg_parent_code}")
	public ResponseEntity<List<CategoryVO>> secondCategory(@PathVariable("cg_parent_code") Integer cg_parent_code)throws Exception{
		
		log.info("1차 카테고리 코드: " + cg_parent_code);
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		entity = new ResponseEntity<List<CategoryVO>>(adCategoryService.getSecondCategoryList(cg_parent_code), HttpStatus.OK);
//		list객체 -> JSON 으로 변환하는 라이브러리(jackson-databind 라이브러리 : pom.xml참고)
		
		
		return entity;
	}
	
}
