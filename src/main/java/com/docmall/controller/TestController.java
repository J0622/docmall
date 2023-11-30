package com.docmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/test/*")
@Controller
public class TestController {
	
	@GetMapping("/testA")
	public void testA() {
		log.info("testA 로그");
	}
	
	@GetMapping("/testB")
	public void testB() {
		log.info("testB 로그");
	}
	
}
