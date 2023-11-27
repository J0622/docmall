package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;
import com.docmall.mapper.AdMemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdMemberServiceImpl implements AdMemberService {
	private final AdMemberMapper adMemberMapper;

	@Override
	public List<MemberVO> member_list(String mbsp_id, Criteria cri) {
		// TODO Auto-generated method stub
		return adMemberMapper.member_list(mbsp_id, cri);
	}
	
	
	
}
