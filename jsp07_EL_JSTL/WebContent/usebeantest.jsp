<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jsp:useBean을 통한 객체 생성</h1>
	<jsp:useBean id="sc" class="com.el.dto.Score" scope="session"></jsp:useBean>
	<!-- java beans : 객체 -->
	<jsp:setProperty property="name" name="sc" value="홍길동"/>
	<jsp:setProperty property="kor" name="sc" value="89"/>
	<jsp:setProperty property="eng" name="sc" value="85"/>
	<jsp:setProperty property="math" name="sc" value="84"/>
	<!-- set : setter 호출 -->
	
	이름 : <jsp:getProperty property="name" name="sc"/><br>
	총점 : <jsp:getProperty property="sum" name="sc"/><br>
	평균 : <jsp:getProperty property="avg" name="sc"/><br>
	등급 : <jsp:getProperty property="grade" name="sc"/><br>
	<!-- get : getter 호출 -->
	
	<button onclick="location.href='res.jsp'">res</button>
</body>
</html>