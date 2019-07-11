package com.answer.biz;

import java.util.List;

import com.answer.dao.answerDaoImpl;
import com.answer.dto.answerDto;
import com.member.dto.memberDto;

public class answerBizImpl implements answerBiz {

	answerDaoImpl dao = new answerDaoImpl();
	
	@Override
	public List<answerDto> selectlist() {
		return dao.selectlist();
	}

	@Override
	public answerDto selectone(int boardno) {
		return dao.selectone(boardno);
	}

	@Override
	public int insert(answerDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(answerDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		return dao.delete(boardno);
	}

	@Override
	public int insertproc(answerDto dto) {
		int updateRes = dao.answerupdateforinsert(dto.getBoardno());
		int insertRes = dao.answerinsert(dto);
		return (updateRes+insertRes);
	}

	@Override
	public memberDto login(String id, String pw) {
		return dao.login(id, pw);
	}
	
	@Override
	public int deleteuser(int memberno) {
		return dao.deleteuser(memberno);
	}

}
