<%@page import="java.util.Date"%>
<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	MVCBoardDto dto = (MVCBoardDto)request.getAttribute("dto");
	int seq = dto.getSeq();
	String writer = dto.getWriter();
	String title = dto.getTitle();
	String content = dto.getContent();
	Date date = dto.getRegdate();
%>

	<h1>상세 글 보기</h1>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td><%= seq %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= writer %></td>
		</tr>
		<tr>
			<th>제  목</th>
			<td><%= title %></td>
		</tr>
		<tr>
			<th>내  용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%= content %></textarea></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%= date %></td>
		</tr>
		<tr>
			<td colspan="5">
				<input type="button" value="수정" onclick="location.href='mycontroller.jsp?command=updateform&seq=<%=dto.getSeq()%>'">
				<input type="button" value="삭제" onclick="location.href='mycontroller.jsp?command=delete&seq=<%=dto.getSeq()%>'">
				<input type="button" value="목록" onclick="location.href='mycontroller.jsp?command=boardlist'">
			</td>
		</tr>
	</table>
</body>
</html>