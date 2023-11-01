package com.docmall.domain;

import java.util.Date;

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
	private String	pro_up_folder;
	private String	pro_img;
	private int		pro_amount;
	private String  pro_buy;
	private Date	pro_date;
	private Date	pro_updatedate;
}
