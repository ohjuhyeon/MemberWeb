<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="notice.model.vo.*" %>
	
<%

Notice notice = (Notice)request.getAttribute("content");

%>	
	
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 수정</title>
</head>
<body>

	<h1>공지 사항 수정</h1>
	<form action="/noticeModifyCommit" method="post">

		<input type="text" size="100" name="subject" value="<%=notice.getSubject() %>"><br><br>
		<textarea rows="30" cols="100" name="content"><%=notice.getContents() %></textarea><br><br>
		<input type="hidden" name="noticeNo" value="<%=notice.getNoticeNo() %>" > <!-- 디비에서 작업을 위해 hidden 사용 -->
		<input type="submit" value="수정"> 
		<input type="reset" value="취소">
	</form>

</body>
</html>