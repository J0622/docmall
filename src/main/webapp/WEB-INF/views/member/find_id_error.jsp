<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 오류</title>
</head>
<body>
	<h1>아이디 찾기 오류</h1>
    <p>${errorMessage}</p>
    <!-- 추가로 필요한 스타일이나 스크립트 등을 여기에 추가할 수 있습니다. -->
    <script>
    alert("해당 정보와 일치하는 회원을 찾을 수 없습니다.");
    window.location.replace("/member/find_id");	
	</script>
</body>
</html>
