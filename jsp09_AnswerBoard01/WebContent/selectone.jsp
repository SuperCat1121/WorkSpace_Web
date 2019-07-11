<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SELECT ONE</title>
<style type="text/css">

	body {
		background: #F7E5DD;
	}

	table {
		margin: 0 auto;
		border-spacing: 0px;
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
	<h1>글 조회</h1>
	<table border="1">
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='answer.do?command=updateform&boardno=${dto.boardno}'">
				<input type="button" value="삭제" onclick="location.href='answer.do?command=delete&boardno=${dto.boardno}'">
				<input type="button" value="목록" onclick="location.href='answer.do?command=selectlist'">
				<input type="button" value="답글쓰기" onclick="location.href='answer.do?command=inputanswerform&boardno=${dto.boardno}'">
			</td>
		</tr>
	</table>
</body>
</html>