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
	<form method="post" action="mvc.do">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="seq" value="${dto.seq}">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>${dto.writer}</td>
			</tr>
			<tr>
				<th>제  목</th>
				<td><input type="text" name="title" value="${dto.title}"></td>
			</tr>
			<tr>
				<th>내  용</th>
				<td><input type="text" name="content" value="${dto.content}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정하기">
					<input type="button" value="취소" onclick="location.href='mvc.do?command=selectone&seq=${dto.seq}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>