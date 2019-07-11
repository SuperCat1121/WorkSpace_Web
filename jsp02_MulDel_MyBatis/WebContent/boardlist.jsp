<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mul.biz.MDBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListPage</title>
<style type="text/css">
	
	html, body {
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}

	table {
		margin: 0 auto;
		border-spacing: 0px;
	}
	
	table th {
		background: pink;
	}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function allChk(bool) {
		// 전체 선택하기
		var chk = document.getElementsByName("chk");
		for(var i=0;i<chk.length;i++) {
			chk[i].checked = bool;
		}
	}
	
	$(function() {
		$("#muldelform").submit(function() {
			if($("#muldelform input:checked").length == 0) {
				alert("하나 이상 체크해 주세요");
				return false;
			}
		});
	});
</script>
</head>
<body>
<%@ include file = "./form/header.jsp" %>
<%
	MDBoardBiz biz = new MDBoardBiz();
	List<MDBoardDto> list = biz.selectList();
%>
	<form action="muldel.jsp" method="post" id="muldelform">
		<table border="1">
			<colgroup>
				<col width="30px">
				<col width="50px">
				<col width="100px">
				<col width="300px">
				<col width="100px">
			</colgroup>
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번  호</th>
				<th>작성자</th>
				<th>제  목</th>
				<th>작성일</th>
			</tr>
<%
			if(list.size() == 0) {
%>
				<tr>
					<td colspan="5">------------------작성된 글이 없습니다----------------</td>
				</tr>
<%
			} else {
				for(int i=0;i<list.size();i++) {
%>
				<tr>
					<td><input type="checkbox" name="chk" value="<%= list.get(i).getSeq() %>"></td>
					<td><%= list.get(i).getSeq() %></td>
					<td><%= list.get(i).getWriter() %></td>
					<td><a href="selectone.jsp?seq=<%=list.get(i).getSeq()%>"><%= list.get(i).getTitle() %></a></td>
					<td><%= list.get(i).getRegdate() %></td>
				</tr>
<%
				}
			}
%>
			<tr>
				<td colspan="5">
					<input type="submit" value="삭제">
					<input type="button" value="글쓰기" onclick="location.href='insert.jsp'">
				</td>
			</tr>
		</table>
	</form>
	<form action="searchres.jsp" method="post">
		<table style="border: none; margin-top: 20px; margin-bottom: 20px;">
			<tr>
				<td>
					<select name="searchoption">
						<option value="seq">글번호</option>
						<option value="writer">작성자</option>
						<option value="title">제목</option>
					</select>
				</td>
				<td>
					<input type="text" name="searchtext">
					<input type="submit" value="검색하기">
				</td>
			</tr>
		</table>
	</form>
<%@ include file = "./form/footer.jsp" %>
</body>
</html>