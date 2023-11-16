<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- 비밀번호 설정 폼 -->
    <form action="/member/set_new_password" method="post">
        <!-- 이전 페이지에서 전달받은 값 -->
        <input type="hidden" name="mbsp_id" value="${mbsp_id}">

        <label for="mbsp_password">New Password:</label>
        <input type="password" id="mbsp_password" name="mbsp_password" required>

        <!-- 비밀번호 확인 -->
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <span style="color: red;"> <!-- 추가된 부분: 에러 메시지를 표시하기 위한 부분 -->
            ${error}
        </span>

        <br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>