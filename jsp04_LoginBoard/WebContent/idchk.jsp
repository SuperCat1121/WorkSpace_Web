<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">

	onload=function() {
		var id = opener.document.getElementsByName("id")[0].value;
		document.getElementsByName("id")[0].value=id;
	}
	System.out.println
	function idConfirm(bool) {
		if(bool == "true") {
			opener.document.getElementsByName("id")[0].title="y";
			opener.document.getElementsByName("pw")[0].focus();
		} else {
			opener.document.getElementsByName("id")[0].focus();
		}
		self.close();
	}

</script>
</head>
<body>
<%
	String idnotused = request.getParameter("idnotused");
%>

	<table border="1">
		<tr>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td><%= idnotused.equals("true")?"id 생성 가능":"중복된 아이디 존재" %>
		</tr>
		<tr>
			<td>
				<!-- idConfirm('') <- '' 붙인 이유 : 문자열로 넘기기 위해서 -->
				<!-- 자바스크립트에서 idConfirm(abc) 로 넘어가면 abc 변수의 값을 찾기 때문에 에러가 난다 -->
				<!-- idConfirm('abc')로 넘어가야 abc 문자열로 함수 실행이 된다 -->
				<input type="button" value="확인" onclick="idConfirm('<%=idnotused%>')">
			</td>
		</tr>
	</table>
</body>
</html>