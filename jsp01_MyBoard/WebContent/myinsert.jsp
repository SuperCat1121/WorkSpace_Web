<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertPage</title>
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
	<h1>InsertPage</h1>
	<form action="myinsertres.jsp" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
			<tr>
				<td>
					<input type="text" name="name">
				</td>
				<td>
					<input type="text" name="mytitle">
				</td>
				<td>
					<input type="text" name="mycontent">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="글 추가">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>