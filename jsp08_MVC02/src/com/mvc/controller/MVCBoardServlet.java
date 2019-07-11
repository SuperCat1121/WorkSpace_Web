package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBoardBizImpl;
import com.mvc.dto.MVCBoardDto;

@WebServlet("/mvc.do")
public class MVCBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MVCBoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		MVCBoardBizImpl biz = new MVCBoardBizImpl();
		PrintWriter out = response.getWriter();
		if(command.equals("selectlist")) {
			List<MVCBoardDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");
		} else if(command.equals("selectone")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = biz.selectone(seq);
			request.setAttribute("dto", dto);
			dispatch(request, response, "selectone.jsp");
		} else if(command.equals("insertform")) {
			response.sendRedirect("insertform.jsp");
		} else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MVCBoardDto dto = new MVCBoardDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res > 0) {
				out.println("<script> alert('글 쓰기 성공');");
				out.println("location.href='mvc.do?command=selectlist'; </script>");
			} else {
				out.println("<script> alert('글 쓰기 실패');");
				out.println("location.href='mvc.do?command=insertform'; </script>");
			}
		} else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = biz.selectone(seq);
			request.setAttribute("dto", dto);
			dispatch(request, response, "updateform.jsp");
		} else if(command.equals("updateres")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MVCBoardDto dto = new MVCBoardDto();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.update(dto);
			if(res > 0) {
				out.println("<script> alert('수정 성공');");
				out.println("location.href='mvc.do?command=selectone&seq=" + seq + "'; </script>");
			} else {
				out.println("<script> alert('수정 실패');");
				out.println("location.href='mvc.do?command=selectone&seq=" + seq + "'; </script>");
			}
		} else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = biz.delete(seq);
			if(res > 0) {
				out.println("<script> alert('삭제 성공');");
				out.println("location.href='mvc.do?command=selectlist'; </script>");
			} else {
				out.println("<script> alert('삭제 실패');");
				out.println("location.href='mvc.do?command=selectone&seq=" + seq + "';</script>");
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
