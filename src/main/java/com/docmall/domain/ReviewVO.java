package com.docmall.domain;

import java.util.Date;

import lombok.Data;

//rew_num, mbsp_id, pro_num, rew_content, rew_score, rew_regdate
@Data
public class ReviewVO {

	
	private Long 	rew_num;
	private String  mbsp_id;
	private Integer pro_num;
	private String  rew_content;
	private int  	rew_score;
	private Date 	rew_regdate;
	
	
}
