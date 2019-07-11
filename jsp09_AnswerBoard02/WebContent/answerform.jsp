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
	<h1>답글 작성</h1>
	<form method="post" action="answer.do">
		<input type="hidden" name="command" value="answerres">
		<input type="hidden" name="boardno" value="${dto.boardno}">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>제  목</th>
				<td><input type="text" name="title" value="RE:${dto.title}"></td>
			</tr>
			<tr>
				<th>내  용</th>
				<td>
					<textarea rows="10" cols="20" name="content">
						${dto.content}
						---------------------
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="완료">
					<input type="button" value="취소" onclick="location.href='answer.do?command=selectone&boardno=${dto.boardno}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>