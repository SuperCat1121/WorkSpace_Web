<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.my.dao.MyBoardDao" %>
<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="java.util.List" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mylist</title>
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
	List<MyBoardDto> list = dao.selectList();
%>
<body>
	<h1>List Page</h1>
	<table border="1">
		<col width="100px">
		<col width="100px">
		<col width="200px">
		<col width="160px">
		<tr>
			<th class="pink">번호</th>
			<th class="pink">이름</th>
			<th class="pink">제목</th>
			<th class="pink">날짜</th>
		</tr>
	<%
		for(int i=0;i<list.size();i++) {
	%>
		<tr>
			<td><%= list.get(i).getMyno() %></td>
			<td><%= list.get(i).getMyname() %></td>
			<td><a href="myselectone.jsp?myno=<%= list.get(i).getMyno() %>"><%= list.get(i).getMytitle() %></a></td>
			<td><%= list.get(i).getMydate() %></td>
		</tr>
	<%
		}
	%>
		<tr>
			<td colspan="4" style="background: #F5ECCE; height: 25px;">
				<input type="button" onclick="location.href='myinsert.jsp'" value="글쓰기">
			</td>
		</tr>
	</table>
</body>
</html>


































