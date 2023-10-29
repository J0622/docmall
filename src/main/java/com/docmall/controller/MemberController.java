package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.dto.LoginDTO;
import com.docmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor  // final 필드만 매개변수가 있는 생성자를 만들어주고 스프링에 의하여 생성자 주입을 받게 된다
@Log4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
//	생성자로 통하여 주입받는 필드의 인터페이스를 사용하는 이유는 유지보수때문이다
	private final MemberService memberService;

	private final PasswordEncoder passwordEncoder;
	
	
	
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
	
//	회원정보 저장 및 저장 후 다른 주소로 이동(redirect)
	@PostMapping("/join")
	public String join(MemberVO vo , RedirectAttributes rttr) {
		
		log.info("회원 정보: " + vo);
		
		vo.setMbsp_password(passwordEncoder.encode(vo.getMbsp_password()));
		
		log.info("암호화된 비밀번호 확인: " + vo.getMbsp_password());
		
		
//		DB저장
		memberService.join(vo);
		
//		redirect가 있으면 이동주소로 해당경로를 인식하게함.
		return "redirect:/member/login";
		
	}
//		로그인 form 페이지
		@GetMapping("/login")
		public void login() {
			
		}
		
//		로그인 인증확인(인증이 확인되면 메인페이지로 이동,단 로그인 실패시 로그인 폼 주소로 이동)
//		String mbsp_id,String mbsp_password를 직접 작성해도 되고
//		LoginDTO클래스를 만들어서 아래와 같이 작성도 가능하다.
		
		@PostMapping("/login")
		public String login(LoginDTO dto , HttpSession session , RedirectAttributes rttr) {
			log.info("로그인: " + dto);
			
//			dto.getMbsp_id()를 잘못입력한 경우 vo는 null이 된다.
			MemberVO db_vo = memberService.login(dto.getMbsp_id());
			
			String url = "";
			String msg = "";
			
//			dto는 사용자가 입력한 패스워드 db_vo는 DB에 저장된 암호화 된 패스워드
//			이때 아이디가 존재하면 true 존재하지 않는다면 false
			if(db_vo != null) {	
//				사용자가 입력한 비밀번호(평문 텍스트:암호화가 안됐다는 뜻)
//				DB에서 가져온 암호화된 비밀번호 일치여부 검사 (matches가 해당 기능을 보유)
				if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password())) {
//					loginStatus이 값을 가지고 사용자의 로그인 상태 여부를 체크
					session.setAttribute("loginStatus", db_vo);
					url = "/";
				}else {
//					로그인 폼 주소로 이동
					url = "/member/login";
					msg = "비밀번호가 일치하지 않습니다.";
//					"문자열",변수
					rttr.addFlashAttribute("msg", msg); // login.jsp파일에서 사용하기 위함
				}
			}else {
//				아이디가 일치하지 않는경우
					url = "/member/login";
					msg = "아이디가 일치하지 않습니다.";
					rttr.addFlashAttribute("msg", msg); // login.jsp파일에서 사용하기 위함
			}
			
			return "redirect:" + url;
			
		}
		
//		로그아웃
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			
			return "redirect:/";
		}
	
	
}
