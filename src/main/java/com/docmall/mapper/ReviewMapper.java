package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;

public interface ReviewMapper {
	
	void review_insert(ReviewVO vo);
	
//	주의사항: Criteria의 검색기능은 사용하지 않음.
	List<ReviewVO> list(@Param("pro_num") Integer pro_num,@Param("cri") Criteria cri);

	int listCount(Integer pro_num);
	
	void delete(Long rew_num);
	
	void review_modify(ReviewVO vo);

	
	






}
