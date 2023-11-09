package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.CategoryVO;
import com.docmall.mapper.UserCategoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@Service
public class UserCategoryServiceImpl implements UserCategoryService {

	private final UserCategoryMapper userCategoryMapper;

	@Override
	public List<CategoryVO> getSecondCategoryList(Integer cg_parent_code) {
		// TODO Auto-generated method stub
		return userCategoryMapper.getSecondCategoryList(cg_parent_code);
	}
	
}
