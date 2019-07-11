package com.my.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.el.dto.Score;

@WebServlet("/controller.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command.equals("basic")) {
			response.sendRedirect("basic-arithmetic.jsp");
		} else if(command.equals("eltest")) {
			Score sc = new Score();
			sc.setName("홍길동");
			sc.setKor(80);
			sc.setEng(70);
			sc.setMath(85);
			
			request.setAttribute("score", sc);
			dispatch(request, response, "eltest.jsp");
		} else if(command.equals("jstltest")) {
			List<Score> res = new ArrayList<Score>();
			for(int i=10;i<50;i+=10) {
				Score sc = new Score();
				sc.setName("이름"+i);
				sc.setKor(50+i);
				sc.setEng(50+i);
				sc.setMath(50+i);
				res.add(sc);
			}
			request.setAttribute("list", res);
			dispatch(request, response, "jstltest.jsp");
		} else if(command.equals("usebean")) {
			response.sendRedirect("usebeantest.jsp");
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


























