package com.answer.biz;

import java.util.List;

import com.answer.dto.MyAnswerDto;

public interface MyAnswerBiz {

	public List<MyAnswerDto> selectList();
	public MyAnswerDto selectOne(int boardno);
	public int insert(MyAnswerDto dto);
	public int update(MyAnswerDto dto);
	public int delete(int boardno);
	
	// dto : 부모의 boardno 을 넣자
	public int answerProc(MyAnswerDto dto);
}
