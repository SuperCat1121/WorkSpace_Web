package com.cal.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cal.dto.CalDto;

public class Util {

	private String toDates;
	
	public String getToDates() {
		return toDates;
	}
	public void setToDates(String mdate) {
		// mdate(yyyyMMddHHmm) -> yyyy-MM-dd hh:mm:00
		// -> yyyy년 MM월 dd일 hh시 mm분 형태로 바꿀거임
		String m = mdate.substring(0, 4) + "-" + mdate.substring(4, 6) + "-" + mdate.substring(6, 8) + " "
				   + mdate.substring(8, 10) + ":" + mdate.substring(10) + ":00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm = Timestamp.valueOf(m);
		// System.out.println(tm);
		toDates = sdf.format(tm);
	}
	
	// 한자리 숫자면 0붙여서 2자리 숫자로 리턴
	public static String isTwo(String msg) {
		String res = msg;
		if(msg.length() < 2) {
			res = "0"+msg;
		}
		return res;
	}
	
	public static String fontColor(int date, int dayOfWeek) {
		String color = null;
		
		if((dayOfWeek+date-1)%7 == 0) {
			color = "blue";
		} else if((dayOfWeek+date-1)%7 == 1) {
			color = "red";
		} else {
			color = "black";
		}
		
		return color;
	}
	
	public static String getCalView(int i, List<CalDto> clist) {
		String d = isTwo(i+"");
		String res = "";
		
		for(CalDto dto : clist) {
			if(dto.getMdate().substring(6, 8).equals(d)) {
				res += "<p>" + ((dto.getTitle().length() > 6)?dto.getTitle().substring(0, 6)+"...":dto.getTitle()) + "</p>";
			}
		}
		
		return res;
	}
}






















