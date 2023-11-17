package com.docmall.mapper;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.OrderVO;

public interface OrderMapper {
	
	int getOrderSeq(); // 주문번호
	
//	1)주문 테이블에 저장될 정보
	void order_insert(OrderVO o_vo);
	
//	2)주문 상세 테이블 저장
	void order_detail_insert(@Param("ord_code")Long ord_code,@Param("mbsp_id") String mbsp_id);
	
//	결제 테이블
	
//	3)장바구니 삭제 테이블
	void cart_del(String mbsp_id);
	
	
}


