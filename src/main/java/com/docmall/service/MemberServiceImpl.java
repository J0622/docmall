package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

//	자동주입
//	@RequiredArgsConstructor : memberMapper필드를 이용한 주입
	private final MemberMapper memberMapper;

	@Override
	public String idCheck(String mbsp_id) {
		
		return memberMapper.idCheck(mbsp_id);
	}

	@Override
	public void join(MemberVO vo) {
		memberMapper.join(vo);
	}

	@Override
	public MemberVO login(String mbsp_id) {

		return memberMapper.login(mbsp_id);
	}

	@Override
	public void modify(MemberVO vo) {
		memberMapper.modify(vo);
	}

	@Override
	public void loginTimeUpdate(String mbsp_id) {
		// TODO Auto-generated method stub
		memberMapper.loginTimeUpdate(mbsp_id);
	}

	@Override
	public void delete(String mbsp_id) {
		// TODO Auto-generated method stub
		memberMapper.delete(mbsp_id);
	}


	
}
