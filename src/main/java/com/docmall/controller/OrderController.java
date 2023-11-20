package com.docmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.MemberVO;
import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderVO;
import com.docmall.domain.PaymentVO;
import com.docmall.dto.CartDTOList;
import com.docmall.kakaopay.ApproveResponse;
import com.docmall.kakaopay.ReadyResponse;
import com.docmall.service.CartService;
import com.docmall.service.KakaoServiceImpl;
import com.docmall.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user/order/*")
public class OrderController {

	private final CartService cartService;
	private final OrderService orderService;
	private final KakaoServiceImpl kakaoServiceImpl;

//	주문정보 페이지
	@GetMapping("/order_info")
	public void order_info(HttpSession session, Model model) throws Exception {

//		주문 목록 작업
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		List<CartDTOList> order_info = cartService.cart_list(mbsp_id);

		double order_price = 0;  

		for (int i = 0; i < order_info.size(); i++) {
			CartDTOList vo = order_info.get(i);
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			order_price += ((double) vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() * 1 / 100))
					* (double) vo.getCart_amount();
		}
		model.addAttribute("order_info", order_info);
		model.addAttribute("order_price", order_price);

	}

//	카카오 페이 결제선택을 진행했을 경우
//	@ResponseBody사용으로 ajax성격 요청
//	OrderVO: 주문테이블 , OrderDetailVO: 주문상세테이블
	@GetMapping(value = "/orderPay", produces = "application/json")
	public @ResponseBody ReadyResponse payReady(String paymethod, OrderVO o_vo, OrderDetailVO od_vo,PaymentVO p_vo ,int totalprice, HttpSession session)
			throws Exception {
		/*
		 1)주문 정보 구성
			 주문테이블(OrderVO): ord_status,payment_status
			 주문상세 테이(OrderDetailVO):
			 	-장바구니 테이블에서 데이터를 참조
			 결제테이블: 현재 보류
			 
		 2)카카오 페이 결제에 필요한 정보구성
			 스프링에서 처리 할수 있는 부분
		*/
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		o_vo.setMbsp_id(mbsp_id); // 아이디 값 할당
		
//		시퀀스를 주문번호로 사용: 동일한 주문번호 값이 사용
		Long ord_code = (long) orderService.getOrderSeq();
		o_vo.setOrd_code(ord_code);
		
		log.info(paymethod);
		log.info(o_vo);
		
		o_vo.setOrd_status("주문성공");
		o_vo.setPayment_status("결제완료");
		
//		주문,주문상세 정보 저장 , 장바구니삭제 (해당기능을 모두 가지고 있음)
		List<CartDTOList> cart_list = cartService.cart_list(mbsp_id);
		String itemName = cart_list.get(0).getPro_name() + "외" +String.valueOf(cart_list.size() -1) + " 건";
		orderService.order_insert(o_vo);
		
//		3) 카카오페이 호출
		ReadyResponse readyResponse = kakaoServiceImpl.payReady(o_vo.getOrd_code(), itemName, cart_list.size(), mbsp_id, totalprice);
		
		log.info(readyResponse.getTid());
		log.info(readyResponse.getNext_redirect_pc_url());
		
//		결제 승인요청 작업에 필요한 정보 (
		session.setAttribute("tid", readyResponse.getTid());
		session.setAttribute("ord_code", o_vo.getOrd_code());
		
		return readyResponse;
	}

//	결제 승인요청 http://localhost:9090//user/order/orderApproval
	@GetMapping("/orderApproval")
	public String orderApproval(@RequestParam("pg_token")String pg_token,HttpSession session) {
		
		Long ord_code = (Long) session.getAttribute("ord_code");
		String tid = (String) session.getAttribute("tid");
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		
		ApproveResponse approveResponse = kakaoServiceImpl.payApprove(ord_code, tid, pg_token, mbsp_id);
		
		session.removeAttribute("ord_code");
		session.removeAttribute("tid");
		
		return "redirect:/user/order/orderComplete";
	}
	
//	결제 성공
	@GetMapping("/orderComplete")
	public void orderComplete() {
		
	}
	
//	결제 취소
	@GetMapping("/orderCancel")
	public void orderCancel(){
		
	}
	
//	결제 실패
	@GetMapping("/orderFail")
	public void orderFail(){
		
	}

}
