<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MDBoardDto dto = new MDBoardDto();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	MDBoardBiz biz = new MDBoardBiz();
	if(biz.insert(dto)) {
%>
		<script type="text/javascript">
			alert("글쓰기 성공");
			location.href="boardlist.jsp";
		</script>
<% } else { %>
		<script type="text/javascript">
			alert("글쓰기 실패");
			location.href="history.back()";
		</script>
<% } %>
</body>
</html>