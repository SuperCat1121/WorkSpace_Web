<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList</title>
</head>
<body>
<%
	List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("list");
%>

	<h1>글 목록</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
	if(list.size()==0) {
%>
		<tr>
			<td colspan="4">-------------작성된 글이 없습니다----------------</td>
		</tr>
<%
	} else {
		for(MVCBoardDto dto : list) {
%>
		<tr>
			<td><%= dto.getSeq() %></td>
			<td><%= dto.getWriter() %></td>
			<td><a href="mycontroller.jsp?command=selectoneform&seq=<%=dto.getSeq()%>"><%= dto.getTitle() %></a></td>
			<td><%= dto.getRegdate() %></td>
		</tr>
<%
		}
	}
%>
		<tr>
			<td colspan="4">
				<input type="button" value="글쓰기" onclick="location.href='mycontroller.jsp?command=insertform'">
			</td>
		</tr>
	</table>
</body>
</html>

































