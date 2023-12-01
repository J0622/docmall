package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.CategoryVO;

// 인터페이스가 Bean으로 생성되는 것이 아니라, 
// 구현클래스에서 사용하기 이전에 외부에서 빈을 생성하고 사용하는것을 의존성 주입(DI) 작업이라고 한다. (면접에서 대답 못하면 떨어짐)
// 매퍼클래스를 상속받는 프록시(Proxy)라는 클래스가 생성이 되고 객체(Bean)생성이 이루어진다.
public interface AdCategoryMapper {

	List<CategoryVO> getFirstCategoryList();
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_parent_code);
		
}
