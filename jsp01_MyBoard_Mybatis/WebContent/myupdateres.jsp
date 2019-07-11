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
	int myno = Integer.parseInt(request.getParameter("myno"));
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");

	MyDao dao = new MyDao();
	MyBoardDto dto = new MyBoardDto();
	
	dto.setMyno(myno);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	
	int res = dao.update(dto);
	if(res > 0) {
%>
		<script type="text/javascript">
			alert("수정 성공!");
			location.href="myselectone.jsp?myno="+<%=myno%>;
		</script>
<% } else { %>
		<script type="text/javascript">
			alert("수정 실패");
			history.back();
		</script>
<% } %>
</body>
</html>