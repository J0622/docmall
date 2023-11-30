package com.docmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.docmall.domain.AdminVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean result = false;

//		현재 클라이언트의 세션을 통한 인증상태에 대한 확인작업을 할 수 있음
		HttpSession session = request.getSession();
		AdminVO user = (AdminVO) session.getAttribute("adminStatus");

		if (user == null) {
//			로그인을 하지 않은 경우 컨트롤러로 실행되지 않도록 하기 위함
//			System.out.println("인터셉터: False");
			result = false;

			if (isAjaxRequest(request)) {
				response.sendError(400);
			} else {
				getTargetUrl(request);

				response.sendRedirect("/admin/admin_menu");
			}

		} else {
//			로그인을 했을 경우 컨트롤러로 진행
			result = true;
		}

		return result;
	}

//	인증되지 않는 상태에서 사용자가 요청한 URL을 저장하고, 로그인 후 URL로 리다이렉트 작업
	private void getTargetUrl(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String uri = request.getRequestURI();
		String query = request.getQueryString();

		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		String targetUrl = uri + query;

		if (request.getMethod().equals("GET")) {
			request.getSession().setAttribute("targetUrl", targetUrl);
		}
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjax = false;

		String header = request.getHeader("AJAX");
		if (header != null && header.equals("true")) {
			isAjax = true;
		}
		return isAjax;
	}
}
