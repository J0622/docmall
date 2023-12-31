<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
</style>

<script>
	let msg = '${msg}';
	if (msg == 'success') {
		alert("회원 수정 완료");
	}
</script>


</head>
<body>

	<%@include file="/WEB-INF/views/comm/header.jsp"%>

	<%@include file="/WEB-INF/views/comm/category_menu.jsp" %>

	<div
		class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-4">Pricing</h1>
		<p class="lead">Quickly build an effective pricing table for your
			potential customers with this Bootstrap example. It’s built with
			default Bootstrap components and utilities with little customization.</p>
	</div>

	<div class="container">
		<div class="card-deck mb-3 text-center">
			<div class="card mb-4 shadow-sm">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Free</h4>
				</div>
				<div class="card-body"></div>
			</div>
			<div class="card mb-4 shadow-sm">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Pro</h4>
				</div>
				<div class="card-body">
					<h1 class="card-title pricing-card-title">
						$15 <small class="text-muted">/ mo</small>
					</h1>
					<ul class="list-unstyled mt-3 mb-4">
						<li>20 users included</li>
						<li>10 GB of storage</li>
						<li>Priority email support</li>
						<li>Help center access</li>
					</ul>
					<button type="button" class="btn btn-lg btn-block btn-primary">Get
						started</button>
				</div>
			</div>
			<div class="card mb-4 shadow-sm">
				<div class="card-header">
					<h4 class="my-0 font-weight-normal">Enterprise</h4>
				</div>
				<div class="card-body">
					<h1 class="card-title pricing-card-title">
						$29 <small class="text-muted">/ mo</small>
					</h1>
					<ul class="list-unstyled mt-3 mb-4">
						<li>30 users included</li>
						<li>15 GB of storage</li>
						<li>Phone and email support</li>
						<li>Help center access</li>
					</ul>
					<button type="button" class="btn btn-lg btn-block btn-primary">Contact
						us</button>
				</div>
			</div>
		</div>

		<%@include file="/WEB-INF/views/comm/footer.jsp"%>
	</div>

	<%@include file="/WEB-INF/views/comm/plugin.jsp"%>
	
	<!-- 카테고리 메뉴 작업 소스 -->
	<!-- 원활한 js경로 사용을 위해서 servlet-context.xml에서 리소스 작업을 진행했음 -->
	<script src="/js/category_menu.js"></script>
</body>
</html>
