<%@page import="com.cal.dto.CalDto"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="com.cal.dao.Util"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src=js/jquery-3.4.1.min.js></script>
<script type="text/javascript">

	$(function() {
		$(".countview").hover(function() {
			var aCountView = $(this);
			var year = $(".y").text().trim();
			var month = $(".m").text().trim();
			var cDate = aCountView.text().trim();
			
			var yyyyMMdd = year + isTwo(month) + isTwo(cDate);
			
			$.ajax({
				url : "CalCountAjax.do",
				type : "post",
				data : "id=kh&yyyyMMdd="+yyyyMMdd,
				dataType : "json",
				async : false,
				success : function(msg) {
					var count = msg.cnt;
					aCountView.after("<div class='cPreview'>"+count+"</div>");
				},
				error : function() {
					alert("통신 실패");
				}
			});
		}, function() {
			$(".cPreview").remove();
		});
	});
	
	function isTwo(n) {
		return (n.length < 2)?"0"+n:n;
	}

</script>
<style type="text/css">

	table {
		margin: 0 auto;
		margin-top: 250px;
		border-spacing: 0px;
	}
	
	table caption {
		background: pink;
	}
	
	a {
		text-decoration: none;
	}
	
	#calendar {
		border-collapse: collapse;
		border: 1px solid gray;
	}
	
	#calendar th {
		width: 80px;
		border: 1px solid gray;
	}
	
	#calendar td {
		width: 80px;
		height: 80px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	
	.clist > p {
		font-size: 5px;
		margin: 1px;
		background: lime;
	}
	
	.cPreview {
		position: absolute;
		top: -30px;
		left: 10px;
		background: pink;
		width: 40px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 40px 40px 40px 1px;
	}

</style>
</head>
<%
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH)+1;
	int day = cal.get(Calendar.DAY_OF_MONTH);
	// System.out.println(month);
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	if(paramYear != null) {
		year = Integer.parseInt(paramYear);
	}
	if(paramMonth != null) {
		month = Integer.parseInt(paramMonth);
	}
	
	if(month > 12) {
		month = 1;
		year++;
	}
	if(month < 1) {
		month = 12;
		year--;
	}
	
	// "해당 년도, 해당 월, 해당 월의 1일" 에 해당하는 Calendar 제작
	// 1일의 요일
	cal.set(year, month-1, 1);
	// System.out.println(month);
	int startDayNum = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일 번호
	// System.out.println(startDayNum);
	int DayCnt = startDayNum; // 요일 번호 카운트
	
	// int day = cal.get(Calendar.DAY_OF_MONTH);
	// System.out.println(year + "년" + month + "월" + day + "일" + startDay);
	
	// 해당 월의 마지막 일 (28, 29, 30, 31)
	int endDayNum = cal.getActualMaximum(Calendar.DATE);
	// System.out.println(endDayNum);
	
	// 달력에 일정 표현
	CalDao dao = new CalDao();
	String yyyyMM = year + Util.isTwo(Integer.toString(month));
	List<CalDto> clist = dao.getCalViewList("kh", yyyyMM);
	
	// 이전 달의 정보
	int beforeYear = year;
	int beforeMonth = month-2;
	List<Integer> beforeList = new ArrayList<Integer>();
	if(beforeMonth < 0) {
		beforeMonth = 11;
		beforeYear -= 1;
	}
	
	cal.set(beforeYear, beforeMonth, 1);
	int beforeLast = cal.getActualMaximum(Calendar.DATE);
	for(int i=startDayNum;i>1;i--) {
		beforeList.add(beforeLast);
		beforeLast--;
	}
	
%>
<body>

	<table border="1" id="calendar">
		<caption>
			<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◀◀</a> <!-- 년도 넘기기 -->
			<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◁</a>  <!-- 월 넘기기 -->
			
			<span class="y"><%=year%></span>년
			<span class="m"><%=month%></span>월
			
			<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▷</a>
			<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▶▶</a>
		</caption>
		<tr>
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		<tr>
		<%
			int idx = beforeList.size();
			for(int i=startDayNum;i>1;i--) {
				out.print("<td style='color:#ccc;'>" + beforeList.get(idx-1) + "</td>");
				idx--;
		 	}

			for(int i=1;i<=endDayNum;i++) {
				if(DayCnt == 8) {
					DayCnt = 1;
					out.print("</tr><tr>");
				}
				DayCnt++;
		%>
			<td>
				<a class="countview" href="cal.do?command=scheduleList&year=<%=year%>&month=<%=month%>&date=<%=i%>" style="color:<%=Util.fontColor(i, startDayNum)%>;"><%=i%></a>
				<a href="insertcalboard.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=endDayNum%>">
					<img alt="일정 추가" src="img/pen.png" style="width:10px; height: 10px;">
				</a>
				<div class="clist">
					<%= Util.getCalView(i, clist) %>
				</div>
			</td>
		<% } %>
		<%
			int endCnt = 1;
			for(int i=DayCnt;i<=7;i++) {
				out.print("<td style='color:#ccc;'>" + endCnt + "</td>");
				endCnt++;
			}
		%>
		</tr>
	</table>

</body>
</html>