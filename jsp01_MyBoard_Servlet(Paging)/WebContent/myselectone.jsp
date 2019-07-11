<%@page import="com.my.dto.MyBoardDto"%>
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
		border-spacing: 0px;
		text-align: center;
	}
	
	table th {
		padding: 5px 10px;
		background: pink;
	}

</style>
</head>
<body>
<%
	MyBoardDto dto = (MyBoardDto)request.getAttribute("dto");
%>
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%= dto.getMyname() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%= dto.getMytitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="width: 150px; height: 100px;"><%= dto.getMycontent() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='my.do?command=updateform&myno=<%= dto.getMyno() %>'">
				<input type="button" value="삭제" onclick="location.href='my.do?command=delete&myno=<%= dto.getMyno() %>'">
				<input type="button" value="목록" onclick="location.href='my.do?command=list'">
			</td>
		</tr>
	</table>
</body>
</html>