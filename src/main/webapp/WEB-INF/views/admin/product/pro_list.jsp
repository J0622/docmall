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
												<td><input type="checkbox"></td>
												<td>${productVO.pro_num}</td>
												<!-- data-이름=값 태그에 저장하기 위한 목적으로 사용됨   -->
												<td>
													<a class="move" href="#" data-bno="${productVO.pro_num}"><img src="">${productVO.pro_up_folder}${productVO.pro_img}</a>
													<a class="move" href="#" data-bno="${productVO.pro_num}">${productVO.pro_name}</a>
												</td>
												<td>${productVO.pro_price}</td>
												<td><fmt:formatDate value="${productVO.pro_date}"
														pattern="yyyy-MM-dd" /></td>
												<td>${productVO.pro_buy}</td>
												<td><button value="수정" class="btn btn-primary">수정</button></td>
												<td><button value="삭제" class="btn btn-danger">삭제</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="box-footer clearfix">
								<div class="row">
									<div class="col-6 text-center">
										<nav aria-label="...">
											<ul class="pagination">
												<!-- 이전 페이지 표시 여부 -->
												<c:if test="${pageMaker.prev }">
													<li class="page-item"><a
														href="/board/list?pageNum=${pageMaker.startPage-1 }"
														class="page-link">Previous</a></li>
												</c:if>
												<!-- 페이지 번호 출력 -->
												<c:forEach begin="${pageMaker.startPage }"
													end="${pageMaker.endPage }" var="num">
													<!--<a class="page-link" href="/board/list?pageNum=${num }">${num }</a> 동작 시
													pageNum은 Criteria를 파라미터로 사용하고 있기때문에 사용하는것이고 pageNum에 값이 들어가면 이게 Setter로 동작해서 Criteria에 전달되서 값이 변경되는것
													이때 amount는 Criteria에 this(1,10)에 의해 기본값 10을 받았기 때문에 아래 구문에 따로 추가하지 않았음 
													pageNum의 경우도 1의 기본값을 받지만 페이징 기능을 위해 값이 변경되도록 한것. -->
													<li
														class='page-item  ${pageMaker.cri.pageNum == num ? "active":"" }'
														aria-current="page">
														<a class="page-link movepage" href="#" data-page="${num }">${num }</a>
													</li>
												</c:forEach>
												<!-- 다음 페이지 표시 여부 -->
												<c:if test="${pageMaker.next }">
													<li class="page-item">
														<a href="/board/list?pageNum=${pageMaker.endPage+1 }" class="page-link " href="#"> Next </a>
													</li>
												</c:if>

											</ul>
										</nav>
									</div>
									<div class="col-6 text-center" >
										<!-- post방식을 사용하면 이전버튼을 누르면 만료된 페이지입니다. 라는 문제가 생긴다. -->
										<!-- 따라서 검색기능을 작업할때는 get방식을 사용한다. -->
										<form action="/board/list" method="get">
											<select name="type">
											<!-- ${pageMaker.cri.type == 'T'? 'selected': ''} 이게 선택한 옵션을 페이지가 새로들어와도 유지하게 해줌 -->
												<option selected>검색종류선택</option>
												<option value="T" ${pageMaker.cri.type == 'T'? 'selected': ''}>제목</option>
												<option value="C" ${pageMaker.cri.type == 'C'? 'selected': ''}>내용</option>
												<option value="W" ${pageMaker.cri.type == 'W'? 'selected': ''}>글작성자</option>
												<option value="TC" ${pageMaker.cri.type == 'TC'? 'selected': ''}>제목 or 내용</option>
												<option value="TW" ${pageMaker.cri.type == 'TW'? 'selected': ''}>제목 or 작성자</option>
												<option value="TWC" ${pageMaker.cri.type == 'TWC'? 'selected': ''}>제목 or 작성자 or 내용</option>
											</select> <input type="text" name="keyword" value="${pageMaker.cri.keyword }" /> 
											<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" /> 
											<input type="hidden" name="amount"  value="${pageMaker.cri.amount}" />
											<button type="submit" class="btn btn-primary">검색</button>
										</form>
										<!-- 페이지 이동 목적으로 클릭할 때 사용 ex)[이전] 1 2 3 4 5 [다음] -->
										<!-- 페이지 이동 목적으로 클릭할 때 사용 :	action="/board/list" -->
										<!-- 목록에서 제목 클릭할때도 사용 이때 주소는 action="/board/get"를 사용 -->
										<form id="actionForm" action="/board/list" method="get">
											<input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
											<input type="hidden" name="amount" 	id="amount"  value="${pageMaker.cri.amount}" />
											<input type="hidden" name="type" 	id="type" 	 value="${pageMaker.cri.type}" /> 
											<input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />
											<input type="hidden" name="bno" 	id="bno"  />
										</form>
									</div>
								</div>
								<a class="btn btn-primary" href="/admin/product/pro_insert" role="button">상품등록</a>
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
	<script src="/bower_components/ckeditor/ckeditor.js"></script>
	<script>
		$(document).ready(function(){
		// ckeditor 환경설정. 자바스크립트 Ojbect문법
		var ckeditor_config = {
				resize_enabled : false,
				enterMode : CKEDITOR.ENTER_BR,
				shiftEnterMode : CKEDITOR.ENTER_P,
				toolbarCanCollapse : true,
				removePlugins : "elementspath", 
				//업로드 탭기능추가 속성. CKEditor에서 파일업로드해서 서버로 전송클릭하면 , 이 주소가 동작된다.
				filebrowserUploadUrl: '/admin/product/imageUpload' 
				}

				CKEDITOR.replace("pro_content",ckeditor_config);
				console.log("ckeditor 버전: ", CKEDITOR.version);

				// 1차 카테고리 선택
				$("#firstCategory").change(function(){
					// $(this) : option 태그중 선택한 option태그를 가리킴
					let cg_parent_code = $(this).val();

					console.log("1차 카테고리 코드", cg_parent_code);

					// 선택한 1차 카테고리에 연관된 2차 카테고리 정보를 가져오는 url
					let url = "/admin/category/secondCategory/" + cg_parent_code; + ".json";

					// 해당 메소드는 스프링에 요청시 데이터를 JSON으로 받는 기능 (ajax기능제공)
					$.getJSON(url, function(secondCategoryList){
						// console.log("2차 카테고리 정보" , secondCategoryList);

						// console.log("2차카테고리 개수", secondCategoryList.length);

						// 2차 카테고리 select 태그 참조
						let secondCategory = $('#secondCategory');
						let optionStr = "";

						secondCategory.find("option").remove();
						secondCategory.append("<option>2차 카테고리</option>");

						// <option value='10'>바지</option>
						for(let i=0; i<secondCategoryList.length; i++){
							optionStr += "<option value= '" +secondCategoryList[i].cg_code + "'>" + secondCategoryList[i].cg_name + "</option>"
						}

						// console.log(optionStr);
						secondCategory.append(optionStr); // 2차 카테고리 <option> 태그들이 추가

					});


				});

									// 파일첨부시 이미지 미리보기
					// 파일첨부에 따른 이벤트 관련 정보를 e라는 매개변수를 통해 참조
					$("#uploadFile").change(function(e){
						let file = e.target.files[0]; // 선택한 파일중 첫번째 파일

						let reader = new FileReader(); // 첨부된 파일을 이용하여 File 객체를 생성하는 기능

						reader.readAsDataURL(file);

						reader.onload = function(e) {
							$("#img_preview").attr("src",e.target.result);
						}
					});

			});
	</script>
</body>
</html>