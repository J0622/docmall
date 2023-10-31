package com.docmall.service;

import com.docmall.domain.AdminVO;

public interface AdminService {
	
	AdminVO admin_ok(String admin_id);
	
	void getRecentLoginTime(String admin_id);
}
