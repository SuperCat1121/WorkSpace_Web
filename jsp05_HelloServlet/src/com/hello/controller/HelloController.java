package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String initParam;
	
    public HelloController() {
        System.out.println("servlet 생성자");
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("Servlet init!!");
    	System.out.println("context-param : " + config.getServletContext().getInitParameter("url"));
    	
    	initParam = config.getInitParameter("driver");
    	System.out.println("init-param : " + initParam);
    	String init2 = config.getInitParameter("d");
    	System.out.println("init2 : " + init2);
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Get 방식으로 들어옴");
    	System.out.println("init-param : " + initParam);
    	
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
    	String command = request.getParameter("command");
    	System.out.println("command : " + command);
    	PrintWriter out = response.getWriter();
    	out.println("<h1 style='background:pink'>Hello Servlet</h1>");
    	out.println("<a href='index.html'>돌아가기</a>");
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Post 방식으로 들어옴");
    	System.out.println("init-param : " + initParam);
    	
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
    	String command = request.getParameter("command");
    	System.out.println("command : " + command);
    	PrintWriter out = response.getWriter();
    	out.println("<h1 style='background:skyblue'>Hello Servlet(Post)</h1>");
    	out.println("<a href='index.html'>돌아가기</a>");
    	
	}
    
    @Override
    public void destroy() {
    	System.out.println("destroy!!");
    }
}
