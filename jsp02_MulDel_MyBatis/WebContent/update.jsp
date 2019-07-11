<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto"%>
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

</style>
</head>
<body>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));

	MDBoardBiz biz = new MDBoardBiz();
	MDBoardDto dto = biz.selectOne(seq);
%>
<%@ include file = "./form/header.jsp" %>
	<form action="updateres.jsp?seq=<%=seq%>" method="post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%= dto.getWriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<%= dto.getTitle() %>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="20" name="content"><%= dto.getContent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정하기">
					<input type="button" value="취소" onclick="location.href='selectone.jsp?seq=<%=seq%>'">
				</td>
			</tr>
		</table>
	</form>
<%@ include file = "./form/footer.jsp" %>
</body>
</html>