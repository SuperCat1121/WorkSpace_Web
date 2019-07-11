<%@page import="java.util.List"%>
<%@page import="com.login.dto.MemberDto"%>
<%@page import="com.login.biz.MemberBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[" + command + "]");
	
	MemberBiz biz = new MemberBiz();
	if(command.equals("login")) {
		String myid = request.getParameter("id");
		String mypw = request.getParameter("pw");
		
		MemberDto login = biz.login(myid, mypw);
		if(login.getMyid() != null) {
			// session
			session.setAttribute("dto", login);
			session.setMaxInactiveInterval(-1);
			// <session 객체>
			// 10분동안 활동이 없으면 session의 'dto' 사라짐.
			// default : 30분
			// 음수 : 무제한
			
			if(login.getMyrole().equals("ADMIN")) {
				response.sendRedirect("adminmain.jsp");
			} else if(login.getMyrole().equals("USER")) {
				response.sendRedirect("usermain.jsp");
			}
		} else {
%>
			<script type="text/javascript">
				alert("login 실패.. id와 pw를 다시 확인하세요");
				location.href="index.jsp";
			</script>
<%
		}
	} else if(command.equals("logout")) {
		session.invalidate();
		// session 정보 삭제
		response.sendRedirect("index.jsp");
	} else if(command.equals("userinfo")) {
		response.sendRedirect("userinfo.jsp");
	} else if(command.equals("update")) {
		response.sendRedirect("updateform.jsp");
	} else if(command.equals("updateres")) {
		MemberDto dto = (MemberDto)session.getAttribute("dto");
		String mypw = request.getParameter("pw");
		String myaddr = request.getParameter("addr");
		String myphone = request.getParameter("phone");
		String myemail = request.getParameter("email");
		dto.setMypw(mypw);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		int res = biz.update(dto);
		if(res > 0) {
%>
			<script type="text/javascript">
				alert("수정 성공");
				location.href="controller.jsp?command=userinfo";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("수정 실패");
				location.href="controller.jsp?command=update";
			</script>
<%
		}
	} else if(command.equals("deleteuser")) {
		MemberDto dto = (MemberDto)session.getAttribute("dto");
		int res = biz.deleteuser(dto.getMyno());
		if(res > 0) {
			session.invalidate();
%>
			<script type="text/javascript">
				alert("회원탈퇴가 완료되었습니다");
				location.href="index.jsp";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("회원탈퇴중 문제가 발생하였습니다. 다시 시도해주세요.");
				location.href="controller.jsp?command=userinfo";
			</script>
<%
		}
	} else if(command.equals("registform")) {
		response.sendRedirect("registform.jsp");
	} else if(command.equals("idchk")) {
		String myid = request.getParameter("id");
		System.out.println(myid);
		String res = biz.idChk(myid);
		System.out.println(res);
		
		// res : null 이면 회원가입 가능
		// res.length() 하면 nullpointerException
		// idnotused : true ==> 아이디가 없음(회원가입 가능)
		//             false ==> 아이디를 쓰고있음(중복됨, 회원가입 불가)
		
		boolean idnotused = true;
		
		if(res != null) {
			idnotused = false;
		}
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	} else if(command.equals("registres")) {
		MemberDto dto = new MemberDto();
		dto.setMyid(request.getParameter("id"));
		dto.setMypw(request.getParameter("pw"));
		dto.setMyname(request.getParameter("name"));
		dto.setMyaddr(request.getParameter("addr"));
		dto.setMyphone(request.getParameter("phone"));
		dto.setMyemail(request.getParameter("email"));
		
		int res = biz.insert(dto);
		if(res > 0) {
%>
			<script type="text/javascript">
				alert("회원가입 성공!!");
				location.href="index.jsp";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("회원가입 실패");
				location.href="controller.jsp?command=registform";
			</script>
<%		
		}
	} else if(command.equals("userlistall")) {
		// ------------------------------ 관리자 처리 시작 -----------------------------
		List<MemberDto> list = biz.userlistall();
		request.setAttribute("list", list);
		pageContext.forward("userlistall.jsp");
	} else if(command.equals("userlist")) {
		List<MemberDto> list = biz.userlist();
		request.setAttribute("list", list);
		pageContext.forward("userlistall.jsp");
	} else if(command.equals("updateroleform")) {
		int myno = Integer.parseInt(request.getParameter("myno"));
		MemberDto dto = biz.selectOne(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("updateroleform.jsp");
	} else if(command.equals("updateroleres")) {
		int myno = Integer.parseInt(request.getParameter("myno"));
		String role = request.getParameter("role");
		
		int res = biz.updaterole(myno, role);
		if(res > 0) {
%>
			<script type="text/javascript">
				alert("수정 성공");
				location.href="controller.jsp?command=userlistall";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("수정 실패");
				location.href="controller.jsp?command=updateroleform&myno="+myno;
			</script>
<%
		}
	}
%>

	<h1>잘못왔다!!</h1>

</body>
</html>


















