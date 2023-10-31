package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.AdminVO;
import com.docmall.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

// @Controller 어노테이션이 존재해야 클라이언트의 요청이 가능하다
// bean 생성 및 등록: adminController 라는 이름으로 생성됨
@Controller // 클라이언트의 요청을 담당하는 기능
@RequestMapping("/admin/*")
@Log4j
@RequiredArgsConstructor
public class AdminController {
	private final AdminService adminService;
	private final PasswordEncoder passwordEncoder;
	
//	관리자 로그인 폼 페이지
	@GetMapping("/intro")
	public String adminLogin() {
		log.info("관리자 로그인 페이지");
		return "/admin/adLogin";
	}
	
//	관리자 메뉴 페이지
	@GetMapping("/admin_menu")
	public void admin_menu(){
		
	}
	
//	관리자 로그인 인증
	@PostMapping("/admin/admin_ok")
	public String admin_ok(AdminVO vo, HttpSession session , RedirectAttributes rttr) {
		
		log.info("로그인: " + vo);

//		dto.getMbsp_id()를 잘못입력한 경우 vo는 null이 된다.
	AdminVO db_vo = adminService.admin_ok(vo.getAdmin_id());

	String url = "";
	String msg = "";

//		dto는 사용자가 입력한 패스워드 db_vo는 DB에 저장된 암호화 된 패스워드
//		이때 아이디가 존재하면 true 존재하지 않는다면 false
	if (db_vo != null) {
//			사용자가 입력한 비밀번호(평문 텍스트:암호화가 안됐다는 뜻)
//			DB에서 가져온 암호화된 비밀번호 일치여부 검사 (matches가 해당 기능을 보유)
		if (passwordEncoder.matches(vo.getAdmin_pw(), db_vo.getAdmin_pw())) {
//				loginStatus이 값을 가지고 사용자의 로그인 상태 여부를 체크
			session.setAttribute("adminStatus", db_vo);

			url = "/admin/admin_menu";

		} else {
//				로그인 폼 주소로 이동
			url = "/admin/intro";
			msg = "비밀번호가 일치하지 않습니다.";
//				"문자열",변수
			rttr.addFlashAttribute("msg", msg); 
		}
	} else {
//			아이디가 일치하지 않는경우
		url = "/admin/intro";
		msg = "아이디가 일치하지 않습니다.";
		rttr.addFlashAttribute("msg", msg); 
	}

	return "redirect:" + url;

	}
	
//	로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/admin/intro";
	}
	
}
