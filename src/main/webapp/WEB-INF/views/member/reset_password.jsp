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
 <!-- 비밀번호 재설정 폼 -->
    <form action="/member/reset_password" method="post">
        <label for="mbsp_id">ID:</label>
        <input type="text" id="mbsp_id" name="mbsp_id" required>

        <label for="mbsp_email">Email:</label>
        <input type="email" id="mbsp_email" name="mbsp_email" required>

        <br>

        <button type="submit">Next</button>
    </form>
    
</body>
</html>