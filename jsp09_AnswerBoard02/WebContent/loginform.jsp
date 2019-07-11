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
	<form method="post" action="answer.do">
		<input type="hidden" name="command" value="loginres">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Login">
					<input type="button" value="회원가입" onclick="location.href='answer.do?command=joinform'">
					<input type="button" value="메인" onclick="location.href='answer.do?command=selectlist'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>