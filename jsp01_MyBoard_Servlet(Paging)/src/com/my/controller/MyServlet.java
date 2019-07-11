package com.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.biz.MyBoardBiz;
import com.my.dto.MyBoardDto;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		MyBoardBiz biz = new MyBoardBiz();
		PrintWriter out = response.getWriter();
		
		if(command.equals("list")) {
			// 1. biz -> dao -> db에 가서 list 리턴
			// 2. request 객체에 담기
			
			int totalcount = biz.totalpage(); // db에 저장된 데이터 수
			int countlist = 2; // 한 화면에 출력될 게시물 수
			int totalpage = totalcount/countlist; // 총 페이지 수
			if(totalcount%countlist > 0) {
				totalpage++;
			}
			int countpage = 4; // 한 화면에 출력될 페이지 수
			int nowpage = Integer.parseInt(request.getParameter("nowpage")); // 현재 페이지
			if(nowpage < 1) {
				nowpage = 1;
			} else if(nowpage > totalpage) {
				nowpage = totalpage;
			}
			int startpage = (((nowpage-1)/countpage)*4)+1;
			int endpage = startpage + (countpage-1);
			if(endpage > totalpage) {
				endpage = totalpage;
			}
			int startcount = ((nowpage-1)*countlist)+1; // 페이지에 따른 글목록 시작지점
			int endcount = countlist*nowpage; // 페이지에 따른 글목록 끝지점
			List<MyBoardDto> list = biz.selectList(startcount, endcount);
			// 1 2 3 4 / 5 6 7 8 / 9 10 11 12 / 13 14 15
			request.setAttribute("list", list);
			request.setAttribute("totalcount", totalcount);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("startpage", startpage);
			request.setAttribute("endpage", endpage);
			dispatch(request, response, "mylist.jsp");
		} else if(command.equals("selectone")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyBoardDto dto = biz.selectone(myno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "myselectone.jsp");
		} else if(command.equals("insertform")) {
			response.sendRedirect("insertform.jsp");
		} else if(command.equals("insertres")) {
			MyBoardDto dto = new MyBoardDto();
			dto.setMyname(request.getParameter("myname"));
			dto.setMytitle(request.getParameter("mytitle"));
			dto.setMycontent(request.getParameter("mycontent"));
			int res = biz.insert(dto);
			if(res > 0) {
				out.println("<script> alert('글 추가 성공') ");
				out.println("location.href='my.do?command=list' </script>");
			} else {
				out.println("<script> alert('글 추가 실패') ");
				out.println("location.href='my.do?command=list' </script>");
			}
		} else if(command.equals("updateform")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyBoardDto dto = biz.selectone(myno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "updateform.jsp");
		} else if(command.equals("updateres")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyBoardDto dto = new MyBoardDto();
			dto.setMyno(myno);
			dto.setMytitle(request.getParameter("mytitle"));
			dto.setMycontent(request.getParameter("mycontent"));
			int res = biz.update(dto);
			if(res > 0) {
				out.println("<script> alert('글 수정 성공') ");
				out.println("location.href='my.do?command=list' </script>");
			} else {
				out.println("<script> alert('글 수정 실패') ");
				out.println("location.href='my.do?command=list' </script>");
			}
		} else if(command.equals("delete")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			int res = biz.delete(myno);
			if(res > 0) {
				out.println("<script> alert('글 삭제 성공') ");
				out.println("location.href='my.do?command=list' </script>");
			} else {
				out.println("<script> alert('글 삭제 실패') ");
				out.println("location.href='my.do?command=list' </script>");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
}


























