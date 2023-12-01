package com.docmall.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.docmall.domain.CategoryVO;
import com.docmall.service.AdCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
 	컨트롤러의 매핑주소 요청이 넘어가기 전, 이 클래스가 먼저 동작 된다.
	지정한 패키지에서 반복적으로 사용하는 Model 데이터 작업을 
	아래 클래스에서 한번만 정의를 해서 사용할 때 이 작업을 진행함
*/
@Log4j
@RequiredArgsConstructor
//	어노테이션은 클래스로 동작이 된다.
@ControllerAdvice(basePackages = {"com.docmall.controller"}) // 이 패키지에 요청이 들어오는 것은 GlobalControllerAdvice 이 클래스에서 동작해 사용하겠다.
public class GlobalControllerAdvice {
	private final AdCategoryService adCategoryService;
	
@ModelAttribute
	public void getFirstCategoryList(Model model) {
		log.info("1차 카테고리 리스트 ");
		
//		현재 사용되는 곳: 관리자 상품등록, 사용자 홈페이지 메인화면
//		반드시 사용해야 하는 기술은 아님, 남발하면 속도에 지장을 줌.
//		사용하는 경우는 해당 기능을 사용하는 곳이 많을 경우 사용하게 됨.
		List<CategoryVO> firstCategoryList = adCategoryService.getFirstCategoryList();
		model.addAttribute("firstCategoryList",firstCategoryList);
		
	}
	
}
