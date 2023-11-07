package com.docmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // 모든 필드를 대상으로 매개변수가 있는 생성자 메소드 생성
@Data
public class ProductDTO {

	private Integer pro_num;
	
	private int 	pro_price;
	
	private String  pro_buy;
	
}
