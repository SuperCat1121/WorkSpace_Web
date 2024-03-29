package com.score;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/score.do")
public class CalScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalScore() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		int sum = kor + eng + math;
		double avg = (double)sum/3;
		
		// json_simple.jar
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("sum", sum);
		obj.put("avg", String.format("%.2f", avg));
		System.out.println("servlet json object : " + obj.toJSONString());
		PrintWriter out = response.getWriter();
		out.println(obj.toJSONString());
		
		System.out.println(name + " : " + kor + " / " + eng + " / " + math);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
