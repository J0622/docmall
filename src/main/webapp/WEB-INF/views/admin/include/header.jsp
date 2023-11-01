<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



    <header class="main-header">
			<!-- Logo -->
			<a href="/admin/admin_menu" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-lg"><b>Admin</b>DocMall</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown messages-menu">
							<a>최근 접속시간:[<fmt:formatDate value="${sessionScope.adminStatus.admin_lastlogin }" pattern="yyyy-MM-dd a hh:mm:ss "/>] </a>
						</li>
					</ul>
					<c:if test="${sessionScope.adminStatus != null }">
					<ul class="nav navbar-nav">
						<li class="dropdown messages-menu">
							<a href="/admin/logout">로그아웃</a>
						</li>
					</ul>
					</c:if>
					<ul class="nav navbar-nav">
						<li class="dropdown messages-menu">
							<a href="/">[DocMall]</a>
						</li>
					</ul>
					
				</div>
			</nav>
		</header>