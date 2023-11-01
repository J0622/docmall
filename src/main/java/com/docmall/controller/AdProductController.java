package com.docmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.AdProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/admin/product/*")
@Controller
@RequiredArgsConstructor
public class AdProductController {
	private final AdProductService adProductService;
	
//	상품 등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {
		
		log.info("상품 등록 폼");
	}
	
}
