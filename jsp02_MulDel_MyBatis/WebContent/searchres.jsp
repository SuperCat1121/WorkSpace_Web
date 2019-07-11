<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="java.util.List"%>
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
<%
	String searchoption = request.getParameter("searchoption");
	String searchtext = request.getParameter("searchtext");
	MDBoardBiz biz = new MDBoardBiz();
	List<MDBoardDto> list = biz.search(searchoption, searchtext);
%>
	<h1>검색 페이지</h1>
	<table border="1">
<%
		for(int i=0;i<list.size();i++) {
%>
		<tr>
			<td><%= list.get(i).getSeq() %></td>
			<td><%= list.get(i).getWriter() %></td>
			<td><a href="selectone.jsp?seq=<%=list.get(i).getSeq()%>"><%= list.get(i).getTitle() %></a></td>
			<td><%= list.get(i).getRegdate() %></td>
		</tr>
<%
		}
%>
		<tr>
			<td>
				<input type="button" value="메인" onclick="location.href='boardlist.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>