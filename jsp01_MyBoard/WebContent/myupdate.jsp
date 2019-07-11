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
<title>Insert title here</title>
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

	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = dao.selectOne(myno);
%>
	<form action="myupdateres.jsp?myno=<%= myno %>" method="post">
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
					<input type="text" name="mytitle" value="<%= dto.getMytitle() %>">
				</td>
				<td>
					<input type="text" name="mycontent" value="<%= dto.getMycontent() %>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정하기">
					<input type="button" value="취소" onclick="history.back()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>