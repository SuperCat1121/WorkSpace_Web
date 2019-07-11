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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.answer.biz.answerBizImpl;
import com.answer.dto.answerDto;
import com.member.dto.memberDto;

@WebServlet("/answer.do")
public class answerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public answerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		PrintWriter out = response.getWriter();
		answerBizImpl biz = new answerBizImpl();
		HttpSession session = request.getSession();
		
		if(command.equals("selectlist")) {
			List<answerDto> list = biz.selectlist();
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");
		} else if(command.equals("selectone")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			answerDto dto = biz.selectone(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "selectone.jsp");
		} else if(command.equals("insertform")) {
			response.sendRedirect("insertform.jsp");
		} else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			answerDto dto = new answerDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res > 0) {
				out.println("<script> alert('글 쓰기 성공');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
			} else {
				out.println("<script> alert('글 쓰기 실패');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
			}
		} else if(command.equals("updateform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			answerDto dto = biz.selectone(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "updateform.jsp");
		} else if(command.equals("updateres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			answerDto dto = new answerDto();
			dto.setBoardno(boardno);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.update(dto);
			if(res > 0) {
				out.println("<script> alert('글 수정 성공');");
				out.println(" location.href='answer.do?command=selectone&boardno=" + boardno + "';</script>");
			} else {
				out.println("<script> alert('글 수정 실패');");
				out.println(" location.href='answer.do?command=updateform&boardno=" + boardno + "';</script>");
			}
		} else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.delete(boardno);
			if(res > 0) {
				out.println("<script> alert('글 삭제 성공');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
			} else {
				out.println("<script> alert('글 삭제 실패');");
				out.println(" location.href='answer.do?command=selectone&boardno=" + boardno + "';</script>");
			}
		} else if(command.equals("answerform")) {
			int parentboardno = Integer.parseInt(request.getParameter("boardno"));
			answerDto dto = biz.selectone(parentboardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "answerform.jsp");
		} else if(command.equals("answerres")) {
			int parentboardno = Integer.parseInt(request.getParameter("boardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			answerDto dto = new answerDto(); // boardno : 부모의 글번호 / 나머지는 다 insert할 내용
			dto.setBoardno(parentboardno);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insertproc(dto);
			if(res > 0) {
				out.println("<script> alert('답글 등록 성공');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
			} else {
				out.println("<script> alert('답글 등록 실패');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
			}
		} else if(command.equals("loginform")) {
			response.sendRedirect("loginform.jsp");
		} else if(command.equals("loginres")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			memberDto dto = biz.login(id, pw);
			if(dto.getMemberid() == null) {
				out.println("<script> alert('아이디와 비밀번호를 다시 확인해주세요');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
			} else {
				out.println("<script> alert('" + id + "님 환영합니다');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
				session.setAttribute("user", dto);
				session.setMaxInactiveInterval(0);
			}
		} else if(command.equals("logout")) {
			session.invalidate();
			out.println("<script> alert('로그아웃 되었습니다');");
			out.println(" location.href='answer.do?command=selectlist';</script>");
		} else if(command.equals("mypage")) {
			response.sendRedirect("mypage.jsp");
		} else if(command.equals("deleteuser")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));
			int res = biz.deleteuser(memberno);
			if(res > 0) {
				out.println("<script> alert('탈퇴가 완료되었습니다');");
				out.println(" location.href='answer.do?command=selectlist';</script>");
				session.invalidate();
			} else {
				out.println("<script> alert('탈퇴중 오류가 발생했습니다');");
				out.println(" location.href='answer.do?command=mypage';</script>");
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
