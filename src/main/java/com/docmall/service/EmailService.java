package com.docmall.service;

import com.docmall.domain.MemberVO;
import com.docmall.dto.EmailDTO;

public interface EmailService {

	void sendMail(EmailDTO dto, String message);
	
	
}
