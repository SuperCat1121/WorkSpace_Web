<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="com.my.dao.MyBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myupdate</title>
<style type="text/css">

	body {
		background: #ccc;
		text-align: center;
		margin-top: 300px;
	}

	table {
		margin: 0 auto;
		border-spacing: 0px;
	}
	
	table td {
		background: white;
		height: 60px;
	}
	
	.pink {
		background: pink;
	}
	
</style>
</head>
<%
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = dao.selectOne(Integer.parseInt(request.getParameter("myno")));
%>
<body>
	<h1>수정하기</h1>
	<form action="myupdateres.jsp?myno=<%= dto.getMyno() %>" method="post">
		<table border="1">
			<colgroup>
				<col width="80px">
				<col width="100px">
			</colgroup>
			<tr>
				<th class="pink">이름</th>
				<td><%= dto.getMyname() %></td>
			</tr>
			<tr>
				<th class="pink">글제목</th>
				<td><input type="text" name="mytitle" value="<%= dto.getMytitle() %>"></td>
			</tr>
			<tr>
				<th class="pink">날짜</th>
				<td><%= dto.getMydate() %></td>
			</tr>
			<tr>
				<th class="pink">글내용</th>
				<td><input type="text" name="mycontent" value="<%= dto.getMycontent() %>"></td>
			</tr>
			<tr>
				<td colspan="2" style="background: #F5ECCE; height: 25px;">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='myselectone.jsp?myno=<%= dto.getMyno() %>'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>