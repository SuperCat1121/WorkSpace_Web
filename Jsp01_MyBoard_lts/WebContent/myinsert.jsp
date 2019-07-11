<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
	<h1>Insert Page</h1>
	<form action="myinsertres.jsp" method="post">
		<table border="1">
			<tr>
				<th class="pink">이름</th>
				<td style="text-align: left;"><input type="text" name="myname" style="width:92%;"></td>
			</tr>
			<tr>
				<th class="pink">제목</th>
				<td style="text-align: left;"><input type="text" name="mytitle" style="width:92%;"></td>
			</tr>
			<tr>
				<th class="pink">내용</th>
				<td><textarea rows="10" cols="60" name="mycontent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="background: #F5ECCE; height: 25px;">
					<input type="submit" value="입력">
					<input type="button" value="목록" onclick="location.href='mylist.jsp'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>