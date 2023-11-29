package com.docmall.domain;

import lombok.Data;

@Data
public class OrderDetailProductVO {
		
//		기존 클래스를 필드로 사용
		private OrderDetailVO orderDetailVO;
		private ProductVO productVO;
	
}
