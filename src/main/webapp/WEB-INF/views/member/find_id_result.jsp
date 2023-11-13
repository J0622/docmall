<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        text-align: center;
        padding-top: 50px; /* 위쪽 여백 조절 */
    }

    h1 {
        margin-bottom: 10px; /* h1 태그 아래 여백 조절 */
    }

    p {
        display: block;
        margin: 0 auto;
    }

    .button-container {
        margin-top: 50px; /* 버튼 위쪽 여백 조절 */
    }
     .btn-primary {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
    }

    .btn-primary:hover {
        color: #fff;
        background-color: #0056b3;
        bord
</style>
</head>
<body>


<h1>아이디 찾기 결과</h1>
<p>회원님의 아이디는 ${foundId} 입니다.</p>

<div class="button-container ">
<button type="button" class="btn btn-primary btn-lg" onclick="goToLoginPage()">로그인 화면으로 돌아가기</button>
</div>

<script>
    function goToLoginPage() {
        window.location.href = "/member/login";
    }
</script>
</body>
</html>