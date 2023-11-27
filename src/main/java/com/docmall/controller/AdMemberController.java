package com.docmall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;
import com.docmall.service.AdMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
public class AdMemberController {
	
	private final AdMemberService adMemberService;
	
	@GetMapping("/member_list")
	public void member_list(String mbsp_id, Criteria cri, Model model) {
		
		List<MemberVO> member_list = adMemberService.member_list(mbsp_id, cri);
		
		model.addAttribute("member_list",member_list);
		
	}
	

}
