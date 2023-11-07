<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Starter</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@ include file="/WEB-INF/views/admin/include/plugin1.jsp"%>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<%@ include file="/WEB-INF/views/admin/include/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/admin/include/nav.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Page Header <small>Optional description</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">

				<!--------------------------
        | Your Page Content Here |
        -------------------------->
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">Product List</h3>
							</div>
							<div class="box-body">
								<div>
									<form action="/admin/product/pro_list" method="get">
										<select name="type">
											<!-- ${pageMaker.cri.type == 'T'? 'selected': ''} 이게 선택한 옵션을 페이지가 새로들어와도 유지하게 해줌 -->
											<option selected>검색종류선택</option>
											<option value="N" ${pageMaker.cri.type=='N' ? 'selected' : '' }>상품명</option>
											<option value="C" ${pageMaker.cri.type=='C' ? 'selected' : '' }>상품번호</option>
											<option value="P" ${pageMaker.cri.type=='P' ? 'selected' : '' }>제조사</option>
											<option value="NP" ${pageMaker.cri.type=='NC' ? 'selected' : '' }>상품명 or 제조사</option>
										</select> <input type="text" name="keyword" value="${pageMaker.cri.keyword }" />
										<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
										<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
										<button type="submit" class="btn btn-primary">검색</button>
									</form>
								</div>
								<table class="table table-bordered">
									<tbody>
										<tr>
											<th style="width: 2%"><input type="checkbox" id="checkAll"></th>
											<th style="width: 8%">상품코드</th>
											<th style="width: 25%">상품명</th>
											<th style="width: 10%">가격</th>
											<th style="width: 20%">등록일</th>
											<th style="width: 15%">판매여부</th>
											<th style="width: 10%">수정</th>
											<th style="width: 10%">삭제</th>
										</tr>
										<!-- items에는 BoardController에 
														model.addAttribute("list", list); "list"와 동일하게 작성 -->
										<!-- var="board" 이거 때문에 BoardVO성격이 되는거임  따라서 ${board.bno}같이 작성가능-->
										<!-- BoardVO성격을 지녔으므로 board가 BoardVO내용을 가짐 getter성격 소유-->
										<c:forEach items="${pro_list }" var="productVO">
											<tr>
												<td><input type="checkbox" name="check" value="${productVO.pro_num}"></td>
												<td>${productVO.pro_num}</td>
												<!-- data-이름=값 태그에 저장하기 위한 목적으로 사용됨   --> <!-- 이미지 작업 -->
												<td>
													<a class="move" href="#" data-bno="${productVO.pro_num}"><img
															src="/admin/product/imageDisplay?dateFolderName=${productVO.pro_up_folder}&fileName=s_${productVO.pro_img}"></a>
													<a class="move" href="#" data-bno="${productVO.pro_num}">${productVO.pro_name}</a>
												</td>
												<td><input type="text" name="pro_price" value="${productVO.pro_price}" ></td>
												<td>
													<fmt:formatDate value="${productVO.pro_date}" pattern="yyyy-MM-dd" />
												</td>
												<td>
													<select name="pro_buy" id="pro_buy">
														<option value="Y" ${productVO.pro_buy}=='Y' ? 'selected' :''>판매가능</option>
														<option value="N" ${productVO.pro_buy}=='N' ? 'selected' :''>판매불가능</option>
													</select>
												</td>
												<!-- id로 접근하게 될 경우 에러맞음 -->
												<!-- foreach문 안에 존재하므로 반복하는데 id는 고유하게 하나만 존재해야 하는 특성에 의해 모순이 생김 -->
												<td><button type="button"  class="btn btn-primary" name="btn_edit">수정</button></td>
												<td><button type="button"  class="btn btn-danger btn_del">삭제</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="box-footer clearfix">
								<div class="row">
									<div class="col-md-4">
										<button type="button"  class="btn btn-primary" id="btn_check_modify1" role="button">
											체크상품 수정1</button>

										<button type="button"  class="btn btn-primary" id="btn_check_modify2" role="button">
											체크상품 수정2</button>
										<form id="actionForm" action="" method="get">
											<input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
											<input type="hidden" name="amount" id="amount" value="${pageMaker.cri.amount}" />
											<input type="hidden" name="type" id="type" value="${pageMaker.cri.type}" />
											<input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />
											
										</form>
									</div>
									<div class="col-md-6 text-center">
										<nav aria-label="...">
											<ul class="pagination">
												<!-- 이전 페이지 표시 여부 -->
												<c:if test="${pageMaker.prev }">
													<li class="page-item"><a href="${pageMaker.startPage-1 }"
															class="page-link movepage">Previous</a></li>
												</c:if>
												<!-- 페이지 번호 출력 -->
												<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="num">
													<li class='page-item  ${pageMaker.cri.pageNum == num ? "active":"" }'
														aria-current="page">
														<a class="page-link movepage" href="${num }" data-page="${num }">${num }</a>
													</li>
												</c:forEach>
												<!-- 다음 페이지 표시 여부 -->
												<c:if test="${pageMaker.next }">
													<li class="page-item">
														<a href="/board/list?pageNum=${pageMaker.endPage+1 }"
															class="page-link movepage " href="#"> Next </a>
													</li>
												</c:if>
				
											</ul>
										</nav>
											</div>
												<div class="col-md-2 text-right">
													<button type="button"  class="btn btn-primary" id="btn_pro_insert" role="button">상품등록</button></div>
							
						</div>
					</div>
				</div>
			</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@ include file="/WEB-INF/views/admin/include/footer.jsp"%>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active"><a href="#control-sidebar-home-tab"
					data-toggle="tab"><i class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:;"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<%@ include file="/WEB-INF/views/admin/include/plugin2.jsp"%>

	<script>
		$(document).ready(function(){
			let actionForm = $("#actionForm");
			$(".movepage").on("click", function(e){

				// [이전] 1 2 3 4 5 [다음] 클릭 이벤트 설정 <a>태그 이용
				e.preventDefault(); // a태그의 링크기능을 제거, href속성에 페이지 번호를 숨겨둠

				actionForm.attr("action", "/admin/product/pro_list");
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));

				// 코딩할 때 동적인 작업을 진행할 시 아래 버튼으로 동작전에 
				// 주석을 걸고 값이 정상적으로 떨어지는지 브라우저에서 확인후 동작시킨다.
				actionForm.submit();

			});
		

		// 목록에서 제목행 체크박스
		let isCheck = true;
			$("#checkAll").on("click", function () {
				$("input[name='check']").prop("checked, this.checked");
				isCheck = this.checked;
			});
			// 목록에서 데이터행 체크박스
			$("input[name='check']").on("click", function () {
				// 제목행 체크상태 변경
				$("#checkAll").prop("checked", this.checked);

				// 데이터 행의 체크박스 상태룰 확인후 제목행 체크상태 변경
				$("input[name='check']").each(function () {
					if (!$(this).is(":checked")) {
						$("#checkAll").prop("checked", false);
					}

				});
			});
				// 체크박스 수정1 버튼 클릭
				$("#btn_check_modify1").on("click", function () {
					// 체크박스 유무 확인
					if ($("input[name='check']:checked").length == 0) {
						alert("수정할 상품을 체크하세요");
						return;
					}
					// 배열문법
					let pro_num_arr = []; // 체크된 상품코드 배열
					let pro_price_arr = []; // 체크된 상품가격 배열
					let pro_buy_arr = []; // 체크된 상품진열 배열

					// 
					$("input[name='check']:checked").each(function () {
						pro_num_arr.push($(this).val());
						pro_price_arr.push($(this).parent().parent().find("input[name = 'pro_price']").val());
						pro_buy_arr.push($(this).parent().parent().find("select[name = 'pro_buy']").val());
					});
					console.log("상품코드: ", pro_num_arr);
					console.log("상품가격: ", pro_price_arr);
					console.log("상품진열: ", pro_buy_arr);

					// dataType: 스프링에서 넘어온 데이터타입(html,text,xml,json등)이 여기에 들어온다.
					$.ajax({
					url: "/admin/product/pro_checked_modify1",
					type: 'post',
					data: { pro_num_arr: pro_num_arr, pro_price_arr: pro_price_arr, pro_buy_arr: pro_buy_arr },
					dataType:'text',
					success:function(result){
						if(result == "success"){
						alert("선택항목이 수정되었습니다.");
						// DB에서 다시 불러오는 작업
						// location.href="/admin/product/pro_list";

						// actionForm.attr("method",get);
						// actionForm.attr("action","/admin/product/pro_list");
						// actionForm.submit();
						}
					}
				});
					

				});
				
				$("#btn_check_modify2").on("click", function () {
					// 체크박스 유무 확인
					if ($("input[name='check']:checked").length == 0) {
						alert("수정할 상품을 체크하세요");
						return;
					}

					// 배열문법
					let pro_num_arr = []; // 체크된 상품코드 배열
					let pro_price_arr = []; // 체크된 상품가격 배열
					let pro_buy_arr = []; // 체크된 상품진열 배열

					// 
					$("input[name='check']:checked").each(function () {
						pro_num_arr.push($(this).val());
						pro_price_arr.push($(this).parent().parent().find("input[name = 'pro_price']").val());
						pro_buy_arr.push($(this).parent().parent().find("select[name = 'pro_buy']").val());
					});
					console.log("상품코드: ", pro_num_arr);
					console.log("상품가격: ", pro_price_arr);
					console.log("상품진열: ", pro_buy_arr);

					// dataType: 스프링에서 넘어온 데이터타입(html,text,xml,json등)이 여기에 들어온다.
					$.ajax({
					url: "/admin/product/pro_checked_modify2",
					type: 'post',
					data: { pro_num_arr: pro_num_arr, pro_price_arr: pro_price_arr, pro_buy_arr: pro_buy_arr },
					dataType:'text',
					success:function(result){
						if(result == "success"){
						alert("선택항목이 수정되었습니다.");


							}
						}
					});
				});

				// 상품등록
				$("#btn_pro_insert").on("click", function(){
					location.href = "/admin/product/pro_insert";
				});

				// 상품수정
				$("button[name='btn_edit']").on("click", function(){
					
					let pro_num = $(this).parent().parent().find("input[name='check']").val();

					console.log(pro_num);

					

					actionForm.find("input[name='pro_num']").remove();

					// <input type="hidden" name="pro_num" id="pro_num" value="25" />

					actionForm.append('<input type="hidden" name="pro_num" id="pro_num" value = "' + pro_num + '" />');

				actionForm.attr("method", "get");
				actionForm.attr("action", "/admin/product/pro_edit");

				
				actionForm.submit();

				});



			}); // ready 이벤트

	</script>

	
</body>
</html>