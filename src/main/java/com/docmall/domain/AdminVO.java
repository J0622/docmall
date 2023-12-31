package com.docmall.domain;

import java.util.Date;

import lombok.Data;

//	CREATE TABLE ADMIN_TBL (
//	    ADMIN_ID    VARCHAR2(15)    PRIMARY KEY,
//	    ADMIN_PW    CHAR(60)    NOT NULL,
//	    ADMIN_VISIT_DATE    DATE
//	);

@Data
public class AdminVO {

	private String admin_id;
	private String admin_pw;
	private Date   admin_visit_date;
	private Date   admin_lastlogin;
	
}
