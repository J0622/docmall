package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;

public interface UserProductMapper {
//	2차 카테고리별 상품리스트(페이징 정보 사용, 검색 제외)
	List<ProductVO> pro_list(@Param("") Integer cg_code,@Param("") Criteria cri);
	
	int getTotalCount(Integer cg_code);
} 
