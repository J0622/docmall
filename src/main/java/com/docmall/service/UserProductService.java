package com.docmall.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;

public interface UserProductService {
	List<ProductVO> pro_list(Integer cg_code,Criteria cri);
	
	int getTotalCount(Integer cg_code);
	
	ProductVO pro_detail(Integer pro_num);
}
