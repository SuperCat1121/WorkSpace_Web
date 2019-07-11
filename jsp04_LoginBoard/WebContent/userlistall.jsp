<%@page import="com.login.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<style type="text/css">

	table {
		margin: 0 auto;
		border-spacing: 0px;
	}
	
	table th {
		background: pink;
	}
	
	table td {
		padding: 4px 8px;
	}

</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
/*
	$(function() {
		$("input[name=change]").click(function() {
			var myno = $(this).parent().siblings().first().text();
			$(location).attr("href", "controller.jsp?command=changerole&myno="+myno);
		});
	});
*/
	function updateRole(myno) {
		location.href="controller.jsp?command=updateroleform&myno="+myno;
	}
</script>
</head>
<body>
<%
	List<MemberDto> list = (List<MemberDto>)request.getAttribute("list");
%>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>주소</th>
			<th>연락처</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>권한</th>
			<th>등급변경</th>
		</tr>
<%
	for(int i=0;i<list.size();i++) {
%>
		<tr>
			<td><%= list.get(i).getMyno() %></td>
			<td><%= list.get(i).getMyid() %></td>
			<td><%= list.get(i).getMyname() %></td>
			<td><%= list.get(i).getMyaddr() %></td>
			<td><%= list.get(i).getMyphone() %></td>
			<td><%= list.get(i).getMyemail() %></td>
			<td><%= list.get(i).getMyenabled().equals("Y")?"가입":"탈퇴" %></td>
			<td><%= list.get(i).getMyrole() %></td>
			<td><input type="button" value="전환" onclick="updateRole(<%= list.get(i).getMyno() %>)"></td>
		</tr>
<% } %>
		<tr>
			<td colspan="9" style="text-align: center;">
				<input type="button" value="메인" onclick="location.href='adminmain.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>