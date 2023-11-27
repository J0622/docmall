package com.docmall.service;

import java.util.List;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;

public interface AdMemberService {
	
	
	List<MemberVO>member_list(String mbsp_id, Criteria cri);
}
