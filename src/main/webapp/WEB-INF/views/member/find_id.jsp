<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="findForm" action="/member/find_id" method="post">
    <div class="form-group">
        <input type="text" name="mbsp_name" id="mbsp_name" placeholder="이름">
    </div>
    <div class="form-group">
        <input type="email" name="mbsp_email" id="mbsp_email" placeholder="이메일">
    </div>
    <button type="submit" id="id-find">아이디 찾기</button>
</form>
</body>
</html>