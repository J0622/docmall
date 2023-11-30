package com.docmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

// 인터셉터 클래스 생성
// HandlerInterceptorAdapter 상속
// 주요 3가지 메소드 재정의 


//	인터셉터 클래스를 사용하기 위해서 servlet-context-xml에서 설정해야 한다.
@Log4j
public class TestInterceptor extends HandlerInterceptorAdapter {
	
//	컨트롤러가 호출되기 전에 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		컨트롤러에서 공통적으로 필요한 작업 진행
		
//		log.info("호출이요");
		
		System.out.println("인터셉터: preHandle 호출");
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("인터셉터: postHandle 호출");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("인터셉터: afterCompletion 호출");
		super.afterCompletion(request, response, handler, ex);
	}

}
