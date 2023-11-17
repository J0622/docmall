package com.docmall.service;

import com.docmall.domain.OrderVO;

public interface OrderService {
	
	int getOrderSeq(); // 주문번호
	
//	주문하기
	void order_insert(OrderVO o_vo);
	
	
	
}
