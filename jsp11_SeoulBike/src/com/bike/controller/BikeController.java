package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.bikeDao;
import com.bike.dto.bikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/bike.do")
public class BikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BikeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		bikeDao dao = new bikeDao();
		PrintWriter out = response.getWriter();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		if(command.equals("first")) {
			response.sendRedirect("bike01.jsp");
		} else if(command.equals("first_db")) {
			String[] tmp = request.getParameterValues("bike");
			//System.out.println("첫번째 문자 배열 : " + tmp[0]);
			List<bikeDto> list = new ArrayList<bikeDto>();
			for(int i=0;i<tmp.length;i++) {
				String[] dtoVal = tmp[i].split("/");
				System.out.println(dtoVal[0] + " / " + dtoVal[1] + " / " + dtoVal[2] + " / " + dtoVal[3] + " / "
				                   + dtoVal[4] + " / " + dtoVal[5] + " / " + dtoVal[6] + " / " + dtoVal[7]);
				bikeDto dto = new bikeDto();
				dto.setRent_id(dtoVal[0]);
				dto.setAddr_gu(dtoVal[1]);
				dto.setContent_id(Integer.parseInt(dtoVal[2]));
				dto.setContent_nm(dtoVal[3]);
				dto.setNew_addr(dtoVal[4]);
				dto.setCradle_count(Integer.parseInt(dtoVal[5]));
				dto.setLongitude(Double.parseDouble(dtoVal[6]));
				dto.setLatitude(Double.parseDouble(dtoVal[7]));
				list.add(dto);
			}
			int res = dao.insert(list);
			if(res > 0) {
				out.println("<script> alert('성공 / 컬럼 수 : " + res + "개');");
				out.println("location.href='index.html';</script>");
			} else {
				out.println("<script> alert('실패');");
				out.println("location.href='index.html';</script>");
			}
		} else if(command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		} else if(command.equals("second_db")) {
			String obj = request.getParameter("obj");
			List<bikeDto> list = new ArrayList<bikeDto>();
			// System.out.println(obj);
			
			// JsonParser : 구글이 만들어놓음
			JsonParser parser = new JsonParser();
			// JsonElement : gson 에서 가장 기본적인 json 요소
			// JSONobject, JSONarray로 바꿀 수 있다. 자유롭게
			// 어떤값이 들어갈지 몰라서 Element로 한다
			JsonElement element = parser.parse(obj);
			for(int i=0;i<element.getAsJsonObject().get("DATA").getAsJsonArray().size();i++) {
				// System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i));
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				JsonElement rent_id_je = tmp.get("rent_id");
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				
				String rent_id = rent_id_je.getAsString();
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();
				
				// System.out.println(rent_id + addr_gu + content_id + content_nm + new_addr + cradle_count + longitude + latitude);
				
				bikeDto dto = new bikeDto();
				dto.setRent_id(rent_id);
				dto.setAddr_gu(addr_gu);
				dto.setContent_id(content_id);
				dto.setContent_nm(content_nm);
				dto.setNew_addr(new_addr);
				dto.setCradle_count(cradle_count);
				dto.setLongitude(longitude);
				dto.setLatitude(latitude);
				list.add(dto);
			}
			int res = dao.insert(list);
			out.println(res);
		}
	}

}




















