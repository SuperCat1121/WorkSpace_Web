<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.dao.MVCBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mycontroller</title>
</head>
<body>

<%
	String command = request.getParameter("command");
	System.out.println("<"+command+">");
	
	MVCBoardDao dao = new MVCBoardDao();
	
	if(command != null) {
		
		// list
		if(command.equals("boardlist")) {
			List<MVCBoardDto> list = dao.selectList();
			request.setAttribute("list", list);
			pageContext.forward("boardlist.jsp");
		// selectoneform
		} else if(command.equals("selectoneform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = dao.selectOne(seq);
			request.setAttribute("dto", dto);
			pageContext.forward("selectoneform.jsp");
		// insertform
		} else if(command.equals("insertform")) {
			response.sendRedirect("boardinsertform.jsp");
		// insertres
		} else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MVCBoardDto dto = new MVCBoardDto(writer, title, content);
			
			int res = dao.insert(dto);
			if(res >0) {
%>
				<script type="text/javascript">
					alert("글 작성 성공");
					location.href="mycontroller.jsp?command=boardlist";
				</script>
<%
			} else {
%>
				<script type="text/javascript">
					alert("글 작성 실패");
					location.href="mycontroller.jsp?command=boardlist";
				</script>
<%
			}
		// updateform
		} else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = dao.selectOne(seq);
			request.setAttribute("dto", dto);
			pageContext.forward("boardupdateform.jsp");
		// updateres
		} else if(command.equals("updateres")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MVCBoardDto dto = new MVCBoardDto();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = dao.update(dto);
			if(res > 0) {
%>
				<script type="text/javascript">
					alert("수정 성공");
					location.href="mycontroller.jsp?command=boardlist";
				</script>
<%
			} else {
%>
				<script type="text/javascript">
					alert("수정 실패");
					location.href="mycontroller.jsp?command=updateform&seq=<%= seq %>";
				</script>
<%
			}
		// delete
		} else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = dao.delete(seq);
			if(res > 0) {
%>
				<script type="text/javascript">
					alert("삭제 성공");
					location.href="mycontroller.jsp?command=boardlist";
				</script>
<%
			} else {
%>
				<script type="text/javascript">
					alert("삭제 실패");
					location.href="mycontroller.jsp?command=selectoneform&seq=<%= seq %>";
				</script>
<%
			}
		}
	}
%>

	<h1>잘못왔음!!</h1>
</body>
</html>













































