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
	String writer = dto.getWriter();
	String title = dto.getTitle();
	String content = dto.getContent();
%>
	<form action="mycontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateres">
		<input type="hidden" name="seq" value="<%= dto.getSeq() %>">
	<%
		// 데이터를 ?(queryString)으로 보내지 않고 form 태그로 보내는 이유 :
		// queryString에 보내면 request head안에 가는데 크기가 정해져있어서 넘치면 요청이 제대로 안될 수 있다
		// 그래서 request 객체 body 안에 넣어서 보내기 위해 form 태그로 보내고 있다
	%>
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%= writer %></td>
			</tr>
			<tr>
				<th>제  목</th>
				<td><input type="text" name="title" value="<%= title %>"></td>
			</tr>
			<tr>
				<th>내  용</th>
				<td><input type="text" name="content" value="<%= content %>"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정하기">
					<input type="button" value="취소" onclick="location.href='mycontroller.jsp?command=selectoneform&seq=<%= dto.getSeq() %>'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>