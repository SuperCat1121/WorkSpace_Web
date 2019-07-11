<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	body {
		background: #F7E5DD;
	}

	table {
		border-spacing: 0px;
		margin: 0 auto;
	}
	
	table th {
		padding: 7px;
		background: pink;
	}
	
	table td {
		background: white;
		padding: 4px;
	}
	
	h1 {
		text-align: center;
	}

</style>
</head>
<body>
	<h1>글 작성</h1>
	<form method="post" action="answer.do">
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
				<td><textarea rows="10" cols="20" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글 추가">
					<input type="button" value="취소" onclick="location.href='answer.do?command=selectlist'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>