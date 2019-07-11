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
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${user.memberid}</td>
		</tr>
		<tr>
			<th>이  름</th>
			<td>${user.membername}</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>${user.memberphone}</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${user.memberrole}</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="회원 탈퇴" onclick="location.href='answer.do?command=deleteuser&memberno=${user.memberno}'">
				<input type="button" value="메인으로" onclick="location.href='answer.do?command=selectlist'">
			</td>
		</tr>
	</table>
</body>
</html>