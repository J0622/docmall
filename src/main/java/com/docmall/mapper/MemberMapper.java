package com.docmall.mapper;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.MemberVO;

public interface MemberMapper {
	
	String idCheck(String mbsp_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbsp_id);
	
	void modify(MemberVO vo);
	
	void loginTimeUpdate(String mbsp_id);
	
	void delete(String mbsp_id);
	
	String findId(@Param("mbsp_name")String mbsp_name,@Param("mbsp_email") String mbsp_email);
}
