package com.docmall.service;

import com.docmall.domain.MemberVO;

public interface MemberService {
	String idCheck(String mbsp_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbsp_id);
	
	void modify(MemberVO vo);
	
	void loginTimeUpdate(String mbsp_id);
	
	void delete(String mbsp_id);

	String findId(String mbsp_name, String mbsp_email);
	
	void updatePw(String mbsp_id, String mbsp_password);
	
	MemberVO checkUserIdAndEmail(String mbsp_id,String mbsp_email);
}
