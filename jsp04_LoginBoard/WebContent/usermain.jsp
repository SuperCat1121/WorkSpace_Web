<%@page import="com.login.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER MAIN</title>
<style type="text/css">

	a {
		text-decoration: none;
	}

</style>
</head>
<body>
<%
	MemberDto dto = (MemberDto)session.getAttribute("dto");
%>
	<h1><%= dto.getMyname() %>님, 환영합니다! (<%= dto.getMyrole() %>) </h1>
	<a href="controller.jsp?command=logout">logout</a>
	
	<div>
		<a href="controller.jsp?command=userinfo">내 정보 보기</a>
	</div>
	
</body>
</html>