package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.AdminVO;
import com.docmall.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // bean 생성 및 등록 : adminServiceImpl(bean이 해당이름으로 생성)
public class AdminServiceImpl implements AdminService {
	
//	public AdminServiceImpl(AdminMapper adminMapper) {
//	this.adminMapper = adminMapper;
//}

//	final 빼먹으면 에러
	private final AdminMapper adminMapper;

	@Override
	public AdminVO admin_ok(String admin_id) {
		// TODO Auto-generated method stub
		return adminMapper.admin_ok(admin_id);
	}

	@Override
	public void getRecentLoginTime(String admin_id) {
		
		adminMapper.getRecentLoginTime(admin_id);
	}


	

	
}
