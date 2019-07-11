<%@page import="com.my.biz.MyBoardBiz"%>
<%@page import="com.my.dao.MyBoardDao"%>
<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
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

	table {
		border-spacing: 0px;
		text-align: center;
	}
	
	table th {
		padding: 5px 5px;
		background: pink;
	}
	
	table td {
		padding: 10px 10px;
	}
	
	a {
		text-decoration: none;
	}

</style>
</head>
<body>
<%
	List<MyBoardDto> list = (List<MyBoardDto>)request.getAttribute("list");
	int totalcount = (int)request.getAttribute("totalcount");
	int totalpage = (int)request.getAttribute("totalpage");
	int nowpage = (int)request.getAttribute("nowpage");
	int startpage = (int)request.getAttribute("startpage");
	int endpage = (int)request.getAttribute("endpage");
%>
	<h1>목록</h1>
	<table border="1">
		<tr>
			<th>번  호</th>
			<th>이  름</th>
			<th>제  목</th>
			<th>작성일</th>
		</tr>
	<% for(MyBoardDto dto : list) { %>
		<tr>
			<td><%= dto.getMyno() %></td>
			<td><%= dto.getMyname() %></td>
			<td><a href="my.do?command=selectone&myno=<%= dto.getMyno() %>"><%= dto.getMytitle() %></a></td>
			<td><%= dto.getMydate() %></td>
		</tr>
	<% } %>
		<tr>
			<td colspan="4">
				<input type="button" value="글쓰기" onclick="location.href='my.do?command=insertform'">
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<a href="my.do?command=list&nowpage=<%= nowpage-1 %>">[이전]&nbsp;&nbsp;&nbsp;</a>
	<%
		for(int i=startpage;i<=endpage;i++) {
			if(i == nowpage) {
	%>
				<b><a href="my.do?command=list&nowpage=<%= i %>">[<%= i %>]</a></b>&nbsp;&nbsp;&nbsp;
	<%
			} else {
	%>
			<a href="my.do?command=list&nowpage=<%= i %>">[<%= i %>]</a>&nbsp;&nbsp;&nbsp;
	<%
			}
		}
	%>
			<a href="my.do?command=list&nowpage=<%= nowpage+1 %>">[다음]&nbsp;&nbsp;&nbsp;</a>
			</td>
		</tr>
	</table>
</body>
</html>