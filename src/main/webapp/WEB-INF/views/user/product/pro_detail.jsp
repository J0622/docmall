<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.101.0">
  <title>Pricing example · Bootstrap v4.6</title>

<!-- Bootstrap Core CSS -->
  <%@include file="/WEB-INF/views/comm/plugin2.jsp" %>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="https://jqueryui.com/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>



  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
    .ui-draggable, .ui-droppable {
      background-position:  top;
    }

    /* 별평점 기본 선택자 */
    p#star_rv_score a.rv_score {
      font-size: 22px;
      text-decoration: none;
      color: lightgray;
    }
    /* 별평점에 마우스 오버 했을 경우 사용되는 선택자 */
    p#star_rv_score a.rv_score.on {
      color: gold;
    }

  </style>
<script>
      $( function() {
      $( "#tabs_pro_detail" ).tabs();
      } );
</script>

</head>
<body>
  
<%@include file="/WEB-INF/views/comm/header.jsp" %>

<%@include file="/WEB-INF/views/comm/category_menu.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
<p>2차 카테고리 : ${cg_name }</p>
</div>

<div class="container">
<div class="card-deck mb-3 text-center row">
  <div class="col-md-6" style="border: solid 1px grey;">
    상품 이미지
    <img class="btn_pro_img" data-pro_num="${productVO.pro_num }" width="100%" height="90%" src="/user/product/imageDisplay?dateFolderName=${productVO.pro_up_folder}&fileName=${productVO.pro_img}" alt="">
  </div>
  <div class="col-md-6">
    <div class="row text-left">
      <div class="col">
        상품이름 : ${productVO.pro_name}
      </div>
    </div>
    <div class="row text-left">
      <div class="col">
          <span id="unit_price">${productVO.pro_price}</span> 
      </div>
    </div>
    <div class="row text-left" >
      <div class="col">
        수량 : <input type="number" id="btn_quantity" value="1" style="width: 80px;">
      </div>
    </div>
    <div class="row text-left">
      <div class="col">
        총 상품금액 : <span id="tot_price">${productVO.pro_price}</span>
      </div>
    </div>
    <div class="row text-left">
      <div class="col-md-6">
        <button type="button" class="btn btn-outline-secondary" name="btn_order" data-pro_num="${productVO.pro_num}">구매하기</button>
      </div>
      <div class="col-md-6">
        <button type="button" class="btn btn-outline-secondary" name="btn_cart_add" data-pro_num="${productVO.pro_num}">장바구니</button> 
      </div>
    </div>
  </div>
</div>
      
                    <form id="actionForm" action="" method="get">
                      <input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
                      <input type="hidden" name="amount"  id="amount" value="${pageMaker.cri.amount}" />
                      <input type="hidden" name="type" id="type" value="${pageMaker.cri.type}" />
                      <input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />

                      <input type="hidden" name="cg_code" id="cg_code" value="${cg_code}" />
                      <input type="hidden" name="cg_name" id="cg_name" value="${cg_name}" />
                    </form>
<div>
<div id="tabs_pro_detail">
  <ul>
    <li><a href="#tabs-prodetail">상품 설명</a></li>
    <li><a href="#tabs-proreview">상품 후기</a></li>
    <li><a href="#tabs-3">Aenean lacinia</a></li>
  </ul>
  <div id="tabs-prodetail">
    <p>${productVO.pro_content}</p>
  </div>
  <div id="tabs-proreview">
  <p>상품후기목록</p>
    <div class="row">
      <div class="col-md-12 text-right">
        <button type="button" id="btn_review_write" class="btn btn-info" data-pro_num="${productVO.pro_num }">상품후기 등록</button>
      </div>
    </div>
  </div>
  <div id="tabs-3">

</div>



</div>
</div>
</div>
<%@include file="/WEB-INF/views/comm/footer.jsp" %>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

<%-- <%@include file="/WEB-INF/views/comm/plugin.jsp" %> --%>
<!-- 카테고리 메뉴 자바 스크립트 작업. -->
<script src="/js/category_menu.js"></script>
<!--상품 후기 Modal-->
<div class="modal fade" id="review_modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="exampleModalLabel">New message</h5>
      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <form>
        <div class="form-group">
          <label for="recipient-name" class="col-form-label">별점 주기</label>
        <p id="star_rv_score">
          <a class="rv_score" href="#">★</a>
          <a class="rv_score" href="#">★</a>
          <a class="rv_score" href="#">★</a>
          <a class="rv_score" href="#">★</a>
          <a class="rv_score" href="#">★</a>
        </p>

        </div>
        <div class="form-group">
          <label for="message-text" class="col-form-label">내용</label>
          <textarea class="form-control" id="rew_content"></textarea>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      <button type="button" id="btn_review_save" class="btn btn-primary" data-pro_num="${productVO.pro_num}">상품 후기 저장</button>
    </div>
  </div>
</div>
</div>

<script>
	$(document).ready(function() {
	
		let actionForm = $("#actionForm");
	
		$(".movepage").on("click", function(event) {
		  event.preventDefault(); // a태그의 href속성에 페이지번호를 숨겨두었다.
	
		  actionForm.attr("action", "/user/product/pro_list");
	
		  // actionForm 태그를 가지고 있는 하위요소중 input 태그의 name 이 pageNum인 것을 찾는 작업
		  actionForm.find("input[name='pageNum']").val($(this).attr("href"));
	
		  actionForm.submit();
		});
	
		// 장바구니 추가
		$("button[name='btn_cart_add']").on("click", function () {
		// console.log("장바구니"); 
		  $.ajax({
			url: '/user/cart/cart_add',
			type : 'post',
			data : {pro_num : $(this).data("pro_num") , cart_amount : $("#btn_quantity").val() },
			success : function(result) {
			  if(result == "success") {
				alert("장바구니에 추가되었습니다");
				  location.href = "/user/cart/cart_list";
			  }
			}
	
		  });
		});
	
		//구매하기(주문)
		$("button[name='btn_order']").on("click", function() {
						
		  let url = "/user/order/order_ready?pro_num=" + $(this).data("pro_num") + "&cart_amount=" + $("#btn_quantity").val();
		  location.href = url;
		});
	
		// 상품 이미지또는 상품명 클릭 시 상세로 보내는 작업.
		$(".btn_pro_img").on("click", function() {
		  console.log("상품상세설명");
		  // 상품 상세 주소 설정
		  actionForm.attr("action", "/user/product/pro_detail");
	
		  let pro_num = $(this).data("pro_num");
	
		  actionForm.find("input[name='pro_num']").remove();
		  // <input type='hidden' name='pro_num' value='상품코드'>
		  actionForm.append("<input type='hidden' name='pro_num' value='"+ pro_num +"'>");
		  actionForm.submit();
		});
	
	
		//수량 변경
		$("#btn_quantity").on("change", function() {
		  
		  let tot_price = parseInt($("#unit_price").text()) * parseInt($("#btn_quantity").val());
	
		  //총 상품금액
		  $("#tot_price").text(tot_price);
	
	
		});
	
		//상품후기작성
		$("#btn_review_write").on("click", function() {
		  $('#review_modal').modal('show');
		});
	
		// 별점
		$("p#star_rv_score a.rv_score").on("click", function(e) {
		  e.preventDefault();
	
		  // this: a태그
		  $(this).parent().children().removeClass("on");
		  $(this).addClass("on").prevAll("a").addClass("on");
	
		});
		
		// 상품명 불러오기
		let reviewPage=1;
		let url = "/user/review/list" + 상품코드 + "/" + reviewPage;

		// 상품 후기 저장
		$("#btn_review_save").on("click" ,function() {
		  // 별평점
		  let rew_score = 0;
		  let rew_content = $("#rew_content").val();
		  $("p#star_rv_score a.rv_score").each(function (index, item) {
			if($(this).attr("class") == "rv_score on") {
			  rew_score += 1;
			}
		  });
	
		  if(rew_score == 0) {
			alert("별 평점을 체크해주세요");
			return;
		  }
	
		  // 후기 체크
		  if(rew_content == "") {
			alert ("상품 리뷰 내용을 작성 해 주세요");
			return;
		  }
		  
			let review_data = {pro_num : $(this).data("pro_num"), rew_content: rew_content, rew_score: rew_score};
		  $.ajax({
			url:'/user/review/new',
			headers :{
			  "Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"
			},
			type : 'post',
			data : JSON.stringify(review_data),
			dataType : 'text',
			success : function(result) {
			  if(result =='success') {
				alert("상품평 등록완료.")
				// 부트 스트랩 4.6 버전의 자바스크립트 명령어
				$('#review_modal').modal('hide');
				return;
			  }
			}
		  });
	
		});
	
	});   
	</script>
</body>
</html>