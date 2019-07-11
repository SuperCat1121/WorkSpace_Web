<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

	table {
		border-spacing: 0px;
	}
	
	table th {
		background: pink;
	}

</style>
</head>
<body>
	<h1>글쓰기</h1>
	<form method="post" action="my.do?command=insertres">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="myname"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="20" rows="10" name="mycontent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글쓰기"></td>
			</tr>
		</table>
	</form>
</body>
</html>