<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="com.my.dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MyDao dao = new MyDao();
	MyBoardDto dto = new MyBoardDto();
	
	String myname = request.getParameter("name");
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	dto.setMyname(myname);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	int res = dao.insert(dto);
	
	if(res > 0) {
%>
		<script type="text/javascript">
			alert("추가 성공!");
			location.href="mylist.jsp";
		</script>
<% } else { %>
		<script type="text/javascript">
			alert("추가 실패");
			history.back();
		</script>
<% } %>
</body>
</html>