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

	MDBoardBiz biz = new MDBoardBiz();
	if(biz.delete(seq)) {
%>
		<script type="text/javascript">
			alert("삭제 성공");
			location.href="boardlist.jsp";
		</script>
<% } else { %>
		<script type="text/javascript">
			alert("삭제 실패")
			location.href="history.back()";
		</script>
<% } %>
</body>
</html>