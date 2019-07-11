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
</head>
<body>
	<c:choose>
		<c:when test="${empty user}">
			<div style="text-align: right; padding-right: 170px;">
				<input type="button" value="로그인" onclick="location.href='answer.do?command=loginform'">
				<input type="button" value="회원가입" onclick="location.href='answer.do?command=joinform'">
			</div>
		</c:when>
		<c:otherwise>
			<div style="text-align: right; padding-right: 170px;">
				<b>${user.memberid}</b> 님 환영합니다!
				<input type="button" value="마이페이지" onclick="location.href='answer.do?command=mypage'">
				<input type="button" value="로그아웃" onclick="location.href='answer.do?command=logout'">
			</div>
		</c:otherwise>
	</c:choose>
	<h1>글 목록</h1>
	<table border="1">
		<col width="100">
		<col width="300">
		<col width="100">
		<col width="100">
		<tr>
			<th>글번호</th>
			<th>제  목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="4">------ 작성된 글이 존재하지 않습니다 -----</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.boardno}</td>
						<td>
							<c:choose>
								<c:when test="${dto.delflag eq 'Y' }">
									<c:out value="--------삭제된 글 입니다--------"></c:out>
								</c:when>
								<c:otherwise>
									<c:forEach begin="1" end="${dto.titletab}">
										&nbsp;
									</c:forEach>
									<a href="answer.do?command=selectone&boardno=${dto.boardno}">${dto.title}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${dto.writer}</td>
						<td>${dto.regdate}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="4">
				<input type="button" value="글 쓰기" onclick="location.href='answer.do?command=insertform'">
			</td>
		</tr>
	</table>
	<!-- 로그인, 글쓸때 로그인 아이디 자동 삽입, 글쓴사람 아니면 수정 삭제 못하게 -->
</body>
</html>