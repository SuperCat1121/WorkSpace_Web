package com.answer.biz;

import java.util.List;

import com.answer.dao.MyAnswerDaoImpl;
import com.answer.dto.MyAnswerDto;

public class MyAnswerBizImpl implements MyAnswerBiz {

	MyAnswerDaoImpl dao = new MyAnswerDaoImpl();
	
	@Override
	public List<MyAnswerDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MyAnswerDto selectOne(int boardno) {
		return dao.selectOne(boardno);
	}

	@Override
	public int insert(MyAnswerDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MyAnswerDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		return dao.delete(boardno);
	}

	@Override
	public int answerProc(MyAnswerDto dto) {
		// update
		int updateRes = dao.updateAnswer(dto.getBoardno());
		// insert
		int insertRes = dao.insertAnswer(dto);
		
		return (updateRes + insertRes);
	}

}
