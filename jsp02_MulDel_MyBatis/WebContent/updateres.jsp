<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="com.mul.biz.MDBoardBiz"%>
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
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	MDBoardBiz biz = new MDBoardBiz();
	MDBoardDto dto = new MDBoardDto();
	dto.setSeq(seq);
	dto.setTitle(title);
	dto.setContent(content);
	
	if(biz.update(dto)) {
%>
		<script type="text/javascript">
			alert("수정 성공");
			location.href="boardlist.jsp";
		</script>
<% } else { %>
		<script type="text/javascript">
			alert("수정 실패");
			location.href="history.back()";
		</script>
<% } %>
</body>
</html>




























