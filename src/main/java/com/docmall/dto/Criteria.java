package com.docmall.dto;



import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//	페이징 및 검색 필드를 가지고 있는 클래스
@ToString
@Setter
@Getter
public class Criteria {
	
//	pageNum은 선택한 페이지 번호를 저장할 필드 (ex:1,2,3,4,5...)
	private int pageNum;
//	페이지마다 출력할 게시물의 개수
	private int amount;

//	검색종류 (제목,글내용,작성자)이런거 , T,C,W,TC,TW,TWC
	private String type;
//	검색어
	private String keyword;

//	기본생성자를 호출하기 위해서 해당 메소드를 생성한것임
	public Criteria() {
//		pageNum, amount 값, 즉 페이지 값을 주는것임
		this(1, 10);
		System.out.println("기본생성자 호출");
	}

//	이 녀석은 기본생성자 생성이 안되므로 위와 같은 작업을 진행
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
//	getter 메소드 대신 사용할 메소드(사용위치:boardMapper.xml)
//	type에 있는 T,C,W,TC,TW,TWC 이 중 하나가 선택 될것임
	public String[] getTypeArr() {
		
//		type이 "TWC"이면 split("")에 의해서 {T,W,C}라는 배열이 생성된다.
		return type == null? new String[] {} : type.split("") ;
	}
	
	public String getListLink() {
	    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
	            .queryParam("pageNum", this.pageNum)
	            .queryParam("amount", this.amount)
	            .queryParam("type", this.type)
	            .queryParam("keyword", this.keyword);

	    return builder.toUriString();
	}
	
}
