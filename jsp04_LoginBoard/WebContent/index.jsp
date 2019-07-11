<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX</title>
<script type="text/javascript">
	function registForm() {
		location.href="controller.jsp?command=registform";
	}
</script>
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
	
	h1 {
		text-align: center;
	}
	
	div {
		margin: 0px;
	}

</style>
</head>
<body>
	<div>
		<h1>LOGIN</h1>
		<form action="controller.jsp" method="post">
			<input type="hidden" name="command" value="login">
			<table border="1">
				<col width="100px">
				<col width="100px">
				<tr>
					<th>I   D</th>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<th>P   W</th>
					<td><input type="text" name="pw"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="submit" value="login">
						<input type="button" value="regist" onclick="registForm()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>