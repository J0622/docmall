<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/comm/plugin2.jsp"%>
<style>
/* 폼 요소를 가운데 정렬하는 스타일 적용 */
#findForm {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 45vh;
	margin: 0;
}

/* 각각의 입력 요소와 버튼을 너비 조정 */
.form-group, #id-find {
	width: 200px;
}
</style>
</head>
<body>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<h5 class="my-0 mr-md-auto font-weight-normal">
			<a href="/">DocMall</a>
		</h5>

		<nav class="my-2 my-md-0 mr-md-3">

			<a class="p-2 text-dark" href="/member/login">로그인</a> <a
				class="p-2 text-dark" href="/member/join">회원가입</a> <a
				class="p-2 text-dark" href="/member/mypage">마이페이지</a> <a
				class="p-2 text-dark" href="#">주문하기</a> <a class="p-2 text-dark"
				href="#">장바구니</a> <a class="p-2 text-dark" href="/admin/intro">[Admin]</a>
		</nav>

	</div>
	<form id="findForm" action="/member/find_id" method="post">
		<div class="form-group">
			<input type="text" name="mbsp_name" id="mbsp_name" placeholder="이름">
		</div>
		<div class="form-group">
			<input type="email" name="mbsp_email" id="mbsp_email"
				placeholder="이메일">
		</div>
		<button type="submit" id="id-find">아이디 찾기</button>
	</form>

	<%@include file="/WEB-INF/views/comm/plugin.jsp"%>
</body>
</html>