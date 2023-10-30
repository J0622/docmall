<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div
	class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
	<h5 class="my-0 mr-md-auto font-weight-normal">
		<a href="/">DocMall</a>
	</h5>
	<c:if test="${sessionScope.loginStatus != null }">
		<div>
			<b>${sessionScope.loginStatus.mbsp_name }님
				(${sessionScope.loginStatus.mbsp_email }) </b> <b>최근 로그인 시간 <fmt:formatDate
					value="${sessionScope.loginStatus.mbsp_lastlogin }"
					pattern="yyyy-MM-dd hh:mm:ss" /></b>
		</div>
	</c:if>

	<nav class="my-2 my-md-0 mr-md-3">
		<c:if test="${sessionScope.loginStatus == null }">
			<a class="p-2 text-dark" href="/member/login">로그인</a>
			<a class="p-2 text-dark" href="/member/join">회원가입</a>
		</c:if>

		<c:if test="${sessionScope.loginStatus != null }">
			<a class="p-2 text-dark" href="/member/logout">로그아웃</a>
			<a class="p-2 text-dark" href="/member/confirmPw">정보수정</a>
		</c:if>

		<a class="p-2 text-dark" href="/member/mypage">마이페이지</a> <a
			class="p-2 text-dark" href="#">주문하기</a> <a class="p-2 text-dark"
			href="#">장바구니</a>
			
	</nav>
	
</div>
<div class="dropdown">
    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        상품 카테고리
    </a>

    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <a class="dropdown-item main-category" href="#" data-category="men">Men</a>
        <a class="dropdown-item main-category" href="#" data-category="women">Women</a>
        <a class="dropdown-item main-category" href="#" data-category="kids">Kids</a>
    </div>
</div>

<div id="men-category" class="sub-category" style="display:none;">
    <a href="#">카테고리 1</a>
    <a href="#">카테고리 2</a>
    <a href="#">카테고리 3</a>
</div>

<div id="women-category" class="sub-category" style="display:none;">
    <a href="#">카테고리 1</a>
    <a href="#">카테고리 2</a>
    <a href="#">카테고리 3</a>
</div>

<div id="kids-category" class="sub-category" style="display:none;">
    <a href="#">카테고리 1</a>
    <a href="#">카테고리 2</a>
    <a href="#">카테고리 3</a>
</div>

<script>
    $(".main-category").click(function() {
        var category = $(this).data('category');
        $(".sub-category").hide();
        $("#" + category + "-category").show();
    });
</script>

