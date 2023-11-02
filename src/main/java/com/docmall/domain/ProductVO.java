package com.docmall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {
	private Integer pro_num;
	private Integer cg_code;
	private String  pro_name;
	private int 	pro_price;
	private int 	pro_discount;
	private String  pro_publisher;
	private String	pro_content;
	
	private String	pro_up_folder; // 스프링에서 별도로 처리
	private String	pro_img;	 // 스프링에서 별도로 처리
	
	private int		pro_amount;
	private String  pro_buy;
	
	private Date	pro_date;
	private Date	pro_updatedate;
	
	// 컨트롤러에 안넣고 여기다가 넣어도 상관없음
//	private MultipartFile uploadFile;

}
