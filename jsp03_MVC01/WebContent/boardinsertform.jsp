<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="com.mvc.dao.MVCBoardDao"%>
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
	MVCBoardDao dao = new MVCBoardDao();
	MVCBoardDto dto = new MVCBoardDto();
%>
	<h1>글 작성</h1>
	<form action="mycontroller.jsp" method="post">
		<input type="hidden" name="command" value="insertres">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>제  목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내  용</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="작성">
					<input type="button" value="취소" onclick="location.href='mycontroller.jsp?command=boardlist'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>