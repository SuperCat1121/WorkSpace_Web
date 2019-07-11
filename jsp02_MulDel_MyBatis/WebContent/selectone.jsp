<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="com.mul.biz.MDBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SelectOnePage</title>
<style type="text/css">

	body {
		width: 100%;
		height: 100%;
		margin: 0px;
	}

	table {
		margin: auto;
		border-spacing: 0px;
	}
	
	table th {
		background: pink;
	}

</style>
</head>
<body>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));

	MDBoardBiz biz = new MDBoardBiz();
	MDBoardDto dto = biz.selectOne(seq);
%>
<%@ include file = "./form/header.jsp" %>
	<table border="1">
		<tr>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<tr>
			<td><%= dto.getWriter() %></td>
			<td><%= dto.getTitle() %></td>
			<td><%= dto.getContent() %></td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" value="수정" onclick="location.href='update.jsp?seq=<%=seq%>'">
				<input type="button" value="삭제" onclick="location.href='delete.jsp?seq=<%=seq%>'">
				<input type="button" value="목록" onclick="location.href='boardlist.jsp'">
			</td>
		</tr>
	</table>
<%@ include file = "./form/footer.jsp" %>
</body>
</html>