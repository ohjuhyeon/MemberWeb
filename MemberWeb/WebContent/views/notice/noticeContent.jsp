<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="notice.model.vo.Notice, java.util.*"%>
<%
	Notice notice = (Notice) request.getAttribute("content");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세내용</title>
</head>
<body>

<h2>제목 : <%=notice.getSubject() %></h2>
<h6>글번호 : <%= notice.getNoticeNo() %> / 글쓴이 : <%= notice.getUserId() %> / 작성일 : <%= notice.getRegDate() %></h6>

<h3> <%= notice.getContents() %></h3>

<a href="/noticeModify?noticeNo=<%= notice.getNoticeNo() %> ">수정</a>
<a href="/notice">목록</a>
<a href="/noticeDelete?noticeNo=<%= notice.getNoticeNo() %>" onclick="return question();">삭제</a>


<script>
	
	function question(){
		var result = window.confirm("정말로 게시글을 삭제 하시겠습니까?");
		
		if(result){
			return true;
		}else {
			return false;
		}
		
	}
	
	
	</script>


</body>
</html>