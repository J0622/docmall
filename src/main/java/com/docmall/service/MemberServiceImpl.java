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

	@Override
	public String findId(String mbsp_name, String mbsp_email) {
		// TODO Auto-generated method stub
		return memberMapper.findId(mbsp_name, mbsp_email);
	}

	@Override
	public void updatePw(String mbsp_id, String mbsp_password) {
		// TODO Auto-generated method stub
		memberMapper.updatePw(mbsp_id, mbsp_password);
	}

	@Override
	public MemberVO  checkUserIdAndEmail(String mbsp_id, String mbsp_email) {
		// 입력한 아이디와 이메일이 일치하는 사용자가 있는지 확인
		return memberMapper.checkUserIdAndEmail(mbsp_id, mbsp_email);
	}

	






	
}
