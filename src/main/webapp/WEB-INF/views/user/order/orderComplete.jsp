<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.101.0">
<title>Pricing example · Bootstrap v4.6</title>

<!-- Bootstrap core CSS -->
<%@include file="/WEB-INF/views/comm/plugin2.jsp"%>


<!-- Favicons -->

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

fieldset {
	border: 2px solid #000; /* 테두리 스타일 및 색상 설정 */
	padding: 20px; /* 내부 패딩 설정 */
	border-radius: 5px; /* 테두리 둥글게 만들기 */
	margin: 10px; /* 필요에 따라 마진 추가 */
}

legend {
	width: auto; /* 너비 자동 조정 */
	padding: 0 10px; /* 필요에 따라 좌우 패딩 추가 */
}
</style>


</head>
<body>

	<%@include file="/WEB-INF/views/comm/header.jsp"%>

	<div class="container">
		<div class="box box-primary">
			<div class="box-body">
				<h3>결제가 완료되었습니다.</h3>
			</div>
		</div>
		<%@include file="/WEB-INF/views/comm/footer.jsp"%>
	</div>

	<%@include file="/WEB-INF/views/comm/plugin.jsp"%>

</body>
</html>
