<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="com.my.dao.MyBoardDao"%>
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
		background: #ccc;
		text-align: center;
		margin-top: 300px;
	}

	table {
		margin: 0 auto;
		border-spacing: 0px;
	}
	
	table td {
		background: white;
		height: 60px;
	}
	
	.pink {
		background: pink;
	}
	
</style>
</head>
<%		
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = new MyBoardDto();
	dto = dao.selectOne(myno);
%>
<body>
	<h1 style="text-align: center;">Select Page</h1>
	<table border="1">
		<colgroup>
			<col width="100px">
			<col width="100px">
			<col width="150px">
		</colgroup>
		<tr style="height: 60px;">
			<th class="pink">이름</th>
			<th class="pink">글 제목</th>
			<th class="pink">날짜</th>
			<th class="pink">글 내용</th>
		</tr>
		<tr style="height: 160px; text-align: center;">
			<td><%= dto.getMyname() %></td>
			<td><%= dto.getMytitle() %></td>
			<td><%= dto.getMydate() %></td>
			<td><textarea rows="10" cols="60" name="mycontent"><%= dto.getMycontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="4" style="padding: 7px 0px; background: #F5ECCE; height: 25px;">
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>'"> &nbsp;
				<input type="button" value="삭제" onclick="location.href='mydeleteres.jsp?myno=<%=dto.getMyno()%>'"> &nbsp;
				<input type="button" value="목록" onclick="location.href='mylist.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>