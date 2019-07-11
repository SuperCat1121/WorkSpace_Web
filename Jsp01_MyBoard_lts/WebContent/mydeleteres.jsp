<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="com.my.dao.MyBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mydeleteres</title>
</head>
<body>
	<%
		MyBoardDao dao = new MyBoardDao();
		int myno = Integer.parseInt(request.getParameter("myno"));
		int res = dao.Delete(myno);
		
		if(res > 0) {
	%>
			<script type="text/javascript">
				alert("삭제 성공!");
				location.href="mylist.jsp";
			</script>
	<% } else { %>
			<script type="text/javascript">
				alert("삭제 실패!");
				location.href="myselectone.jsp?myno="+<%=myno%>;
			</script>
	<% } %>
</body>
</html>