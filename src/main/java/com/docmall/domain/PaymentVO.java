package com.docmall.domain;

import java.util.Date;

import lombok.Data;

// pay_code, odr_code, mpsp_id, pay_method, pay_date, pay_tot_price, 
// pay_nobank_price, pay_rest_price, pay_nobank_user, pay_nobank, pay_memo

@Data
public class PaymentVO {
	private Integer pay_code;
	private Integer odr_code;
	private String  mpsp_id;
	private String  pay_method;
	private Date  	pay_date;
	private int 	pay_tot_price;
	private int 	pay_nobank_price;
	private int 	pay_rest_price;
	private String  pay_nobank_user;
	private String  pay_nobank;
	
	private String  pay_memo;
	
	
}
