package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.MyAnswerBizImpl;
import com.answer.dto.MyAnswerDto;

@WebServlet("/answer.do")
public class MyAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyAnswerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		MyAnswerBizImpl biz = new MyAnswerBizImpl();
		PrintWriter out = response.getWriter();
		
		if(command.equals("selectlist")) {
			List<MyAnswerDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");
		} else if(command.equals("selectone")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			MyAnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "selectone.jsp");
		} else if(command.equals("insertform")) {
			response.sendRedirect("insertform.jsp");
		} else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyAnswerDto dto = new MyAnswerDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res > 0) {
				out.println("<script> alert('글 추가 성공');");
				out.println("location.href='answer.do?command=selectlist'; </script>");
			} else {
				out.println("<script> alert('글 추가 실패');");
				out.println("location.href='answer.do?command=selectlist'; </script>");
			}
			
		} else if(command.equals("updateform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			MyAnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "updateform.jsp");
		} else if(command.equals("updateres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyAnswerDto dto = new MyAnswerDto();
			dto.setBoardno(boardno);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.update(dto);
			if(res > 0) {
				out.println("<script> alert('수정 성공');");
				out.println("location.href='answer.do?command=selectone&boardno=" + boardno + "'; </script>");
			} else {
				out.println("<script> alert('수정 실패');");
				out.println("location.href='answer.do?command=selectone&boardno=" + boardno + "'; </script>");
			}
		} else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.delete(boardno);
			if(res > 0) {
				out.println("<script> alert('삭제 성공');");
				out.println("location.href='answer.do?command=selectlist'; </script>");
			} else {
				out.println("<script> alert('삭제 실패');");
				out.println("location.href='answer.do?command=selectlist'; </script>");
			}
		} else if(command.equals("inputanswerform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			MyAnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "inputanswerform.jsp");
		} else if(command.equals("inputanswerres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyAnswerDto dto = new MyAnswerDto(); // boardno : 부모 글번호 / 나머지는 다 답글 추가할 내용
			dto.setBoardno(boardno);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.answerProc(dto);
			if(res > 0) {
				out.println("<script> alert('답글 추가 성공');");
				out.println("location.href='answer.do?command=selectlist'; </script>");
			} else {
				out.println("<script> alert('답글 추가 실패');");
				out.println("location.href='answer.do?command=selectlist'; </script>");
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
