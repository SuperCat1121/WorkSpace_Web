package com.answer.biz;

import java.util.List;

import com.answer.dto.answerDto;
import com.member.dto.memberDto;

public interface answerBiz {

	public List<answerDto> selectlist();
	public answerDto selectone(int boardno);
	public int insert(answerDto dto);
	public int update(answerDto dto);
	public int delete(int boardno);
	
	public int insertproc(answerDto dto);
	
	public memberDto login(String id, String pw);
	public int deleteuser(int memberno);
}