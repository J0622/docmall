package com.docmall.domain;

import lombok.Data;

//	관리자 기능
//	주문상세 정보 저장 목적(주문 상세 테이블과 상품 테이블 조인 결과)
//	OT.ORD_CODE, OT.PRO_NUM, OT.DT_AMOUNT,P.PRO_NUM, P.PRO_NAME, P.PRO_PRICE, P.PRO_UP_FOLDER, P.PRO_IMG

@Data
public class OrderDetailInfoVO {
	
//	OrderDetailVO 및 ProductVO 데이터 조인
	private Long 	ord_code; 		// DB 시퀀스 사용
	private Integer pro_num;
	private String  pro_name;
	private int 	pro_price;
	private int 	dt_amount;
	
//	필수 요소는 아니고 여러 방식을 보여주기 위함.
	private int 	ord_price; 		// 주문금액(pro_price * dt_amount)
	
//	이미지 & 이미지 경로
	private String	pro_up_folder; 	// 스프링에서 별도로 처리
	private String	pro_img;
	
	
	
	
	
}
