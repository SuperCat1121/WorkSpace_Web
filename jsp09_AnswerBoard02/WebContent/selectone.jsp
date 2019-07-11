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
	<h1>글 조회</h1>
	<table border="1">
		<tr>
			<th>제  목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<th>내  용</th>
			<td><textarea rows="10" cols="20" readonly="readonly">${dto.content}</textarea></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${dto.writer eq user.memberid}">
					<input type="button" value="수정" onclick="location.href='answer.do?command=updateform&boardno=${dto.boardno}'">
					<input type="button" value="삭제" onclick="location.href='answer.do?command=delete&boardno=${dto.boardno}'">
				</c:if>
				<input type="button" value="목록" onclick="location.href='answer.do?command=selectlist'">
				<input type="button" value="답글" onclick="location.href='answer.do?command=answerform&boardno=${dto.boardno}'">
			</td>
		</tr>
	</table>
</body>
</html>