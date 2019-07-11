package com.my.biz;

import java.util.List;

import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;

public class MyBoardBiz {

	MyBoardDao dao = new MyBoardDao();
	
	public List<MyBoardDto> selectList(int startcount, int endcount) {
		return dao.selectList(startcount, endcount);
	}
	
	public MyBoardDto selectone(int myno) {
		return dao.selectone(myno);
	}
	
	public int insert(MyBoardDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}
	
	public int delete(int myno) {
		return dao.delete(myno);
	}
	
	public int totalpage() {
		return dao.totalpage();
	}
}
