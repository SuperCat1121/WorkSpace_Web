<%@page import="com.login.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Expires", "0");
	/*
		데이터가 변경되었을 때, 이전 내용을 화면에 보여주는 이유
		-> 서버값이 아닌 캐시에 저장된 내용을 출력
		
		브라우저가 캐시에 응답결과를 저장하지 않도록 설정
		response.setHeader("Pragma", "no-cache");        // http1.0 일 때
		response.setHeader("Cache-control", "no-store"); // http1.1 일 때
		response.setHeader("Expires", "0");              // proxy server 일 때
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN MAIN</title>
</head>
<body>
<%
	MemberDto dto = (MemberDto)session.getAttribute("dto");
	if(dto == null) {
		pageContext.forward("index.jsp");
	}
%>
	<h1><%= dto.getMyname() %>님 환영합니다</h1>
	<a href="controller.jsp?command=logout">logout</a>
	<div>
		<a href="controller.jsp?command=userlistall">회원정보 전체 조회</a>
	</div>
	<div>
		<a href="controller.jsp?command=userlist">회원정보 조회(ENABLED=Y)</a>
	</div>
</body>
</html>




























