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

	body {
		width: 100%;
		height: 100%;
		margin: 0px;
	}

	table {
		margin: 0 auto;
		border-spacing: 0px;
	}
	
	table th {
		background: pink;
	}
	
	textarea {
		margin: 0px;
	}

</style>
</head>
<body>
<%@ include file = "./form/header.jsp" %>
	<form action="insertres.jsp" method="post">
		<table border="1">
			<colgroup>
				<col width="60px">
				<col width="70px">
				<col width="150px">
			</colgroup>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="writer">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="20" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="button" value="취소" onclick="location.href='boardlist.jsp'">
				</td>
			</tr>
		</table>
	</form>
<%@ include file = "./form/footer.jsp" %>
</body>
</html>