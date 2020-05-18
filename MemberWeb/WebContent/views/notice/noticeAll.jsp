<%@page import="notice.model.vo.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="notice.model.vo.Notice, java.util.*"%>

<%
	PageData pd = (PageData) request.getAttribute("PageData");
	ArrayList<Notice> noticeList = pd.getPageList();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
</head>
<body>
	<center>

		<h1>공지사항</h1>
		<table border="1">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
			</tr>
			<%
				for (Notice notice : noticeList) {
			%>
			<tr>
				<td><%=notice.getNoticeNo()%></td>
				<td><%=notice.getSubject()%></td>
				<td><%=notice.getUserId()%></td>
				<td><%=notice.getRegDate()%></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="4" align="center"><%=pd.getPageNavi()%></td>

				<!-- //< 1 2 3 4 5 >   
   //currentPage : 현재 페이지 번호
   //rescordCountPage : 1페이지에 몇개의 게시물 출력이 되는지
   //recordTotalCount : 전체 게시물의 개수
   //start :검색할 게시물 시작 번호 
   //end : 검색할 게시물 끝 번호 -->

			</tr>
		</table>
	</center>

</body>
</html>