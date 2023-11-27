package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;

public interface AdMemberMapper {
	
	List<MemberVO>member_list(@Param("mbsp_id") String mbsp_id,@Param("cri") Criteria cri);
	
}
