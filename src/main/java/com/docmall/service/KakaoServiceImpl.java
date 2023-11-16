package com.docmall.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.docmall.kakaopay.ApproveResponse;
import com.docmall.kakaopay.ReadyResponse;

// 카카오 페이 API서버에 실제 요청하는 작업
/*
 * https://minkwon4.tistory.com/178 (참고용)
 * https://jung-story.tistory.com/132 (참고용)
 * HttpURLConnection 을 이용한 방법
 * RestTemplate을 이용한 통신방법 (스프링 권장)
 */
@Service
public class KakaoServiceImpl {
	
//	HttpHeaders 클래스: 헤더정보를 구성할 때 사용
	/*
	 * 2)헤더정보
	 * Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
	 * Content-type: application/x-www-form-urlencoded;charset=utf-8 
	 */
	
//	결제 준비하기 (1차 요청)
	/*
	 * 1)요청주소: https://kapi.kakao.com/v1/payment/ready
	 * POST: v1/payment/ready
	 * Host: kapi.kakao.com
	 * 
	 * 2)헤더정보
	 * Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
	 * Content-type: application/x-www-form-urlencoded;charset=utf-8
	 */
	
	private HttpHeaders getHeadersInfo() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","KakaoAK 363d6bd86792a02246b901f078fdcaef");
		headers.set("Content","application/x-www-form-urlencoded;charset=utf-8");

		return headers;
	}
	
	public ReadyResponse payReady(Long odr_code, String itemName, int quantity, String mbsp_id, int totalAmount) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		
		parameters.add("cid", "TC0ONETIME"); 				// 가맹점 코드, 10자
		parameters.add("partner_order_id", String.valueOf(odr_code));	// 가맹점 주문번호, 최대 100자
		parameters.add("partner_user_id", mbsp_id);	// 가맹점 회원 id, 최대 100자
		parameters.add("item_name", itemName);			// 상품명, 최대 100자
		parameters.add("quantity", String.valueOf(quantity));			// 상품 수량
		parameters.add("total_amount", String.valueOf(totalAmount));		// 상품 총액
		parameters.add("tax_free_amount", "0");	// 상품 비과세 금액
		
		parameters.add("approval_url", "http://localhost:9090//user/order/orderApproval");		// 결제 성공 시 redirect url, 최대 255자
		parameters.add("cancel_url", "http://localhost:9090//user/order/orderCancel");			// 결제 취소 시 redirect url, 최대 255자
		parameters.add("fail_url", "http://localhost:9090//user/order/orderFail");			// 결제 실패 시 redirect url, 최대 255자
	
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(parameters, this.getHeadersInfo());
		
//		Kakao API 서버와 통신
		RestTemplate template = new RestTemplate();
		
		String kakaoReadyUrl = "https://kapi.kakao.com/v1/payment/ready";
		
		ReadyResponse readyResponse = template.postForObject(kakaoReadyUrl, requestEntity, ReadyResponse.class);
		
		return readyResponse;
		
	}
	
//	결제 준비하기 (2차 요청)
	/*
	 * 1)요청주소: https://kapi.kakao.com/v1/payment/approve
	 * POST: v1/payment/approve
	 * Host: kapi.kakao.com
	 * 
	 * 2)헤더정보
	 * Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
	 * Content-type: application/x-www-form-urlencoded;charset=utf-8
	 */
	
	public ApproveResponse payApprove(Long odr_code, String tid, String pgToken, String mbsp_id) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		
		parameters.add("cid", "TC0ONETIME");
		parameters.add("tid", tid);
		parameters.add("partner_order_id", String.valueOf(odr_code));
		parameters.add("partner_user_id", mbsp_id);
		parameters.add("pg_token", pgToken);
	
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(parameters, this.getHeadersInfo());
		
//		Kakao API 서버와 통신
		RestTemplate template = new RestTemplate();
		
		String kakaoApproveUrl = "https://kapi.kakao.com/v1/payment/approve";
		
		ApproveResponse approveResponse = template.postForObject(kakaoApproveUrl, requestEntity, ApproveResponse.class);
		
		return approveResponse;
	
	}
	
	
	
	
	

	
	
	
}
