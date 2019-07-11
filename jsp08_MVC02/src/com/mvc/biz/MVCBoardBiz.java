package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MVCBoardDaoImpl;
import com.mvc.dto.MVCBoardDto;

public interface MVCBoardBiz {
	
	MVCBoardDaoImpl dao = new MVCBoardDaoImpl();

	public List<MVCBoardDto> selectList();
	public MVCBoardDto selectone(int seq);
	public int insert(MVCBoardDto dto);
	public int update(MVCBoardDto dto);
	public int delete(int seq);
}
