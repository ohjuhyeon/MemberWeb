<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>로그인 페이지</h1>
	<form action="/login" method="post">

		ID : <input type="text" name="userId" id="userId"> <br>
		PW : <input type="password" name="userPwd" id="userPwd"> <br>
		<input type="submit" value="로그인"> 
		<input type="reset" value="취소">
		
		<a href="#">회원가입</a>
		<a href="#">공지사항</a>
	</form>

</body>
</html>