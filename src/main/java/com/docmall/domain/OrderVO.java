package com.docmall.domain;

import lombok.Data;

@Data
public class OrderVO {
	
	private Long 	ord_code; // DB 시퀀스 사용
	private String 	mbsp_id;  // 인증세션에서 참조
	
//	클라이언트 즉, 주문정보 페이지에서 전송한걸 받아서 쓸 데이터
	private String 	ord_name;
	private String 	ord_zipcode;
	private String 	ord_addr_basic;
	private String 	ord_addr_detail;
	private String 	ord_tel;
	private int 	ord_price;
	
	private String 	ord_regdate; // sysdate
	
	
	private String 	ord_status;
	private String 	payment_status;
}
