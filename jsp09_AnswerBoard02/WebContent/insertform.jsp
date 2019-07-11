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
	<h1>글 작성</h1>
	<form method="post" action="answer.do">
		<input type="hidden" name="command" value="insertres">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${user.memberid}" readonly="readonly"></td>
			</tr>
			<tr>
				<th>제  목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내  용</th>
				<td><textarea rows="10" cols="20" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글 쓰기">
					<input type="button" value="취소" onclick="location.href='answer.do?command=selectlist'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>