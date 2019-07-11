<%@page import="com.el.dto.Score"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	table {
		margin: 0 auto;
		border-spacing: 0px;
	}
	
	table th {
		padding: 10px;
		background: pink;
	}
	
	table td {
		text-align: center;
		padding: 5px;
	}
	
	h1 {
		text-align: center;
	}

</style>
</head>
<body>
	<h1>JSTL TEST</h1>
	<!-- Jsp Standard Tag Library -->

	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		<c:forEach items="${list}" var="score">
			<tr>
				<td>
					<c:if test="${score.name eq '이름10'}">
						<c:out value="홍길동"></c:out>
					</c:if>
					<c:choose>
						<c:when test="${score.name eq '이름20'}">
							<c:out value="${score.name}님"></c:out>
						</c:when>
						<c:when test="${score.name eq '이름30'}">
							<c:out value="${score.name}"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="누구지?"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${score.kor}</td>
				<td>
					<!-- choose 안에 주석 쓰면 안됨!! -->
					<c:choose>
						<c:when test="${score.eng >= 80}">
							<c:out value="${score.eng} 멋져"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="${score.eng}"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${score.math}</td>
				<td>${score.sum}</td>
				<td>${score.avg}</td>
				<td>
					<c:choose>
						<c:when test="${score.grade eq 'A' || score.grade eq 'B'}">
							<c:out value="PASS"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="FAIL"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<table border="1">
		<c:forEach var="i" begin="2" end="9" step="1">
			<tr>
				<th>${i}단</th>
				<c:forEach var="j" begin="1" end="9">
					<td>
						${i} * ${j} = ${i*j}
						<c:if test="${j==9}"><br></c:if>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
<%
	/*
		1. 영어 점수가 80 점 이상이면 --멋져
		2. 등급이 A이거나 B면 PASS 나머진 FAIL 출력
	*/
%>
</body>
</html>