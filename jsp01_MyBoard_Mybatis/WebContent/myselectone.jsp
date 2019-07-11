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
<title>SelectOnePage</title>
<style type="text/css">
	table {
		text-align: center;
		border-spacing: 0px;
	}
	
	table th {
		background: pink;
	}
</style>
</head>
<body>
<%
	int myno = Integer.parseInt(request.getParameter("myno"));

	MyDao dao = new MyDao();
	MyBoardDto dto = dao.selectone(myno);
	
%>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>제목</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>
				<%= dto.getMyname() %>
			</td>
			<td>
				<%= dto.getMytitle() %>
			</td>
			<td>
				<%= dto.getMycontent() %>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align: right;">
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=myno%>'">
				<input type="button" value="삭제" onclick="location.href='mydeleteres.jsp?myno=<%=myno%>'">
				<input type="button" value="목록" onclick="location.href='mylist.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>