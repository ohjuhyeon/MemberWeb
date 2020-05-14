<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>

<%
	Member member = (Member) session.getAttribute("member");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>멤버 로그인</title>
</head>
<body>


	<%
		if (member != null) {
	%>
	[<%=member.getUserName()%>] 님 환영합니다.
	<a href="/logout">로그아웃 </a><br>
	<a href="#">마이페이지</a>
	<a href="#">업로드</a>
	<a href="#">다운로드</a>
	<a href="#">전체회원조회</a>
	<br>

	<%
		} else {
	%>
	<h1>로그인 페이지</h1>
	<form action="/login" method="post">

		ID : <input type="text" name="userId" id="userId"> <br>
		PW : <input type="password" name="userPwd" id="userPwd"> <br>
		<input type="submit" value="로그인"> <input type="reset"
			value="취소"> <a href="#">회원가입</a> <a href="#">공지사항</a>
	</form>
	<%
		}
	%>

</body>
</html>