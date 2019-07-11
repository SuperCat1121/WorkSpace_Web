<%@page import="com.my.dao.MyDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.my.dto.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListPage</title>
<style type="text/css">
	body {
		background: #ccc;
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		background: white;
		text-align: center;
		border-spacing: 0px;
		margin: 0 auto;
	}
	
	table th {
		background: pink;
	}
</style>
</head>
<body>
	<%
		MyDao dao = new MyDao();
		List<MyBoardDto> list = dao.selectList();
	%>
	<h1>ListPage</h1>
	<table border="1">
		<colgroup>
			<col width="100px">
			<col width="100px">
			<col width="160px">
			<col width="130px">
		</colgroup>
		<tr style="height:60px;">
			<th>글 번호</th>
			<th>닉네임</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>
	<%
		for(int i=0;i<list.size();i++) {
	%>
		<tr style="height:50px;">
			<td><%= list.get(i).getMyno() %></td>
			<td><%= list.get(i).getMyname() %></td>
			<td><a href="myselectone.jsp?myno=<%=list.get(i).getMyno()%>"><%= list.get(i).getMytitle() %></a></td>
			<td><%= list.get(i).getMydate() %></td>
		</tr>
	<% } %>
		<tr>
			<td colspan="4" style="text-align: right; background: #F2F5A9;">
				<input type="button" value="글쓰기" onclick="location.href='myinsert.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>