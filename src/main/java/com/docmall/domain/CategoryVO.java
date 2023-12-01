package com.docmall.domain;

//	cg_code
//	cg_parent_code
//	cg_name


public class CategoryVO {
	 
//	테이블의 컬럼명과 필드명을 동일하게 함.
//	만약 컬럼명과 필드명을 다르게 사용할 경우 컬럼명에 별칭을 주거나 Mybatis resultMap 사용으로 데체.
//	resultMap은 이외에도 여러기능이 있으나 시간이 없어서 설명불가능(찾아보는게 좋을수도?)
	private Integer cg_code; // 모든 카테고리 코드
	private Integer cg_parent_code; // 1차 카테고리 코드
	private String  cg_name;
	
//	롬복의 Getter,Setter 어노테이션이 아래와 같은 작업을 해줌
	public Integer getCg_code() {
		return cg_code;
	}
	public void setCg_code(Integer cg_code) {
		this.cg_code = cg_code;
	}
	public Integer getCg_parent_code() {
		return cg_parent_code;
	}
	public void setCg_parent_code(Integer cg_parent_code) {
		this.cg_parent_code = cg_parent_code;
	}
	public String getCg_name() {
		return cg_name;
	}
	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	
//	롬복의 toString 어노테이션이 아래 역할 구동
	@Override
	public String toString() {
		return "CategoryVO [cg_code=" + cg_code + ", cg_parent_code=" + cg_parent_code + ", cg_name=" + cg_name + "]";
	}
	
	
}
