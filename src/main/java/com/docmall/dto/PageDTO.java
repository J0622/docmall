package com.docmall.dto;

import lombok.Getter;
import lombok.ToString;

//	페이징 기능 구현
@Getter
@ToString
public class PageDTO {
//	1 ,11,21 와 같은 시작페이지
    private int startPage;
//    10,20,30과 같은 마지막 표시 페이지
    private int endPage;
//    이전과 다음을 담당할 녀석들
    private boolean prev,next;
//    테이블의 데이터 총 개수 (기능을 담당하는 축이 됨)
    private int total;
//    1)페이징:pageNum,amount 2)검색:type,keyword
    private Criteria cri;

    public PageDTO(Criteria cri, int total) {
    	/*
    	 1번 사례
    	 total: 13
    	 amount: 5
    	 위 내용에 의거하여 페이지수는 3
    	 이유: amount는 한 페이지에 보여줄 페이지번호의 수이고 total은 페이지 번호의 총 개수이기 때문에
    	 따라서 페이지의 개수 = total/amount 의 몫과 나머지가 된다. 13/5 = 몫2 나머지3 , 2+1 = 3
    	 */
        this.cri = cri;
        this.total = total;

//      한 블럭당 보여줄 페이지 번호의 개수
        int pageSize = 10; // 검색조건이 없을 경우 :pageNum=1, amount=10, type =null, keyword=null
        int endPageInfo = pageSize - 1;  // 

//       ceil 함수는 소수점을 강제로 반올림 하기 때문에 1~10 어떤 페이지를 선택해도 end페이지의 10이 나오게 됨
        this.endPage = (int) (Math.ceil(cri.getPageNum() / (double) pageSize)) * pageSize;
//       10-9 = 1 이므로 1이 스타트 페이지가 됨
        this.startPage = this.endPage - endPageInfo;
        
//       해당 페이지에 실제 데이터가 존재 해야 유령 페이지가 아니므로 해당 코드작성 , 1.0을 사용하는 이유는 해당 값이 정수이면 안되고 실수여야 반올림 기능이 발생하므로 
//        int readEnd = (int) (Math.ceil(13 * 1.0 / 5()));  2.6이 ceil 동작되서 3이되서 페이지는 3
        int readEnd = (int) Math.ceil((total * 1.0) / cri.getAmount());
        
//        3<=10 이므로 내부 this동작 따라서 엔드페이지가 실제 엔드페이지 수만큼만 표시됨
        if (readEnd <= this.endPage) {
            this.endPage = readEnd;
        }
//        스타트 페이지가 1이고 > 기준이 1이므로 false이다 따라서 이전 기능은 화면에 미출력된다.
        this.prev = this.startPage > 1;
//        엔드페이지가 10이고 실제 엔드페이지는 3이므로 false이다 따라서 다음 기능은 화면에 미출력된다.
        this.next = this.endPage < readEnd;
		
//        cri: pageNum=1, amount=10, type =null, keyword=null
//        startPage, endPage, pageSize, prev, next
	}

}
