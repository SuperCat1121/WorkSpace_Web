package com.cal.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cal.dao.CalDao;
import com.cal.dao.Util;
import com.cal.dto.CalDto;

@WebServlet("/cal.do")
public class CalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CalServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		CalDao dao = new CalDao();
		
		if(command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
		} else if(command.equals("insertcal")) {
			String id = request.getParameter("id");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("day");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			
			String mdate = year + Util.isTwo(month) + Util.isTwo(date) + Util.isTwo(hour) + Util.isTwo(min);
			int res = dao.insertCalBoard(new CalDto(0, id, title, content, mdate, null));
			
			if(res > 0) {
				response.sendRedirect("cal.do?command=calendar");
			} else {
				dispatch(request, response, "error.jsp");
			}
		} else if(command.equals("scheduleList")) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			
			String mdate = year + Util.isTwo(month) + Util.isTwo(date);
			request.setAttribute("list", dao.dtoList("kh", mdate));
			dispatch(request, response, "scheduleList.jsp");
		}  else if(command.equals("")) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}