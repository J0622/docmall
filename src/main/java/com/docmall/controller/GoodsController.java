package com.docmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor // final 필드만 매개변수가 있는 생성자를 만들어주고 스프링에 의하여 생성자 주입을 받게 된다
@Log4j
@RequestMapping("/goods/**")
@Controller
public class GoodsController {
    @GetMapping("/goodsmain")
    public String goodsMain() {
        return "goodsmain"; // 뷰의 이름 (Thymeleaf 또는 JSP 등)
    }
}
