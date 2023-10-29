<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/">DocMall</a></h5>
  <nav class="my-2 my-md-0 mr-md-3">
  	<c:if test="${sessionScope.loginStatus == null }">
    <a class="p-2 text-dark" href="/member/login">로그인</a>
    <a class="p-2 text-dark" href="/member/join">회원가입</a>
    </c:if>
    
    <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-dark" href="/member/logout">로그아웃</a>
    <a class="p-2 text-dark" href="/member/modify">정보수정</a>
    </c:if>
    
    <a class="p-2 text-dark" href="#">마이페이지</a>
    <a class="p-2 text-dark" href="#">주문하기</a>
    <a class="p-2 text-dark" href="#">장바구니</a>
  </nav>
</div>