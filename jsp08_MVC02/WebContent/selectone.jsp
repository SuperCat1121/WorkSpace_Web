<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<th>제  목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내  용</th>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='mvc.do?command=updateform&seq=${dto.seq}'">
				<input type="button" value="삭제" onclick="location.href='mvc.do?command=delete&seq=${dto.seq}'">
				<input type="button" value="목록" onclick="location.href='mvc.do?command=selectlist'">
			</td>
		</tr>
	</table>
</body>
</html>