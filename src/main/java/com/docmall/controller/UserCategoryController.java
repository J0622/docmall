package com.docmall.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.domain.CategoryVO;
import com.docmall.service.UserCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController  // ajax문법만 존재할때 사용가능, jsp파일에 사용은 되지 않고 데이터만 가져오기 위해 작업
@Log4j
@RequiredArgsConstructor
@RequestMapping("/category/*")
public class UserCategoryController {
	
	private final UserCategoryService userCategoryService;
	
	@ResponseBody
	@GetMapping("/secondCategory/{cg_parent_code}")
	public ResponseEntity<List<CategoryVO>> secondCategory(@PathVariable("cg_parent_code") Integer cg_parent_code)throws Exception{
		
		log.info("1차 카테고리 코드: " + cg_parent_code);
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		entity = new ResponseEntity<List<CategoryVO>>(userCategoryService.getSecondCategoryList(cg_parent_code), HttpStatus.OK);
//		list객체 -> JSON 으로 변환하는 라이브러리(jackson-databind 라이브러리 : pom.xml참고)
		
		
		return entity;
	}
	
	
	
}
