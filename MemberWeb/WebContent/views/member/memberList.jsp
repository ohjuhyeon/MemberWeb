<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"
	import="java.util.ArrayList"%>

<%
	ArrayList<Member> list = (ArrayList<Member>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>관리자 : 회원관리 페이지</h1>
	<table border="1">

		<tr>

			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>주소</th>
			<th>성별</th>
			<th>취미</th>
			<th>가입날짜</th>

		</tr>
		<%for (Member mOne : list) {%>
		<tr>
			<td><%=mOne.getUserId()%></td>
			<td><%=mOne.getUserName()%></td>
			<td><%=mOne.getAge()%></td>
			<td><%=mOne.getEmail()%></td>
			<td><%=mOne.getPhone()%></td>
			<td><%=mOne.getAddress()%></td>
			<td><%=mOne.getGender()%></td>
			<td><%=mOne.getHobby()%></td>
			<td><%=mOne.getEnrollDate()%></td>
		</tr>
		<%}%>
	</table>
	<a href="index.jsp">메인 페이지로 이동 </a>


</body>
</html>