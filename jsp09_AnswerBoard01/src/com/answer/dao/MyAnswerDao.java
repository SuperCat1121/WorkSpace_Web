package com.answer.dao;

import java.util.List;

import com.answer.dto.MyAnswerDto;

public interface MyAnswerDao {
	
	String SELECT_LIST_SQL = "SELECT BOARDNO, GROUPNO, GROUPSQ, TITLETAB, TITLE,"
			+ " CONTENT, WRITER, REGDATE FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ";
	String SELECT_ONE_SQL = "SELECT BOARDNO, GROUPNO, GROUPSQ, TITLETAB, TITLE,"
			+ " CONTENT, WRITER, REGDATE FROM ANSWERBOARD WHERE BOARDNO=?";
	String INSERT_SQL = "INSERT INTO ANSWERBOARD VALUES (BOARDNOSEQ.NEXTVAL,GROUPNOSEQ.NEXTVAL,1,0,"
			             + " ?, ?, ?, SYSDATE)";
	String UPDATE_SQL = "UPDATE ANSWERBOARD SET TITLE=?, CONTENT=? WHERE BOARDNO=?";
	String DELETE_SQL = "DELETE FROM ANSWERBOARD WHERE BOARDNO=?";
	
	String UPDATE_ANSWER_SQL = "UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ+1"
			                    + " WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?)"
			                    + "       AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO=?)";
	String INSERT_ANSWER_SQL = "INSERT INTO ANSWERBOARD"
			                    + " VALUES (BOARDNOSEQ.NEXTVAL,"
			                    + " (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?),"
			                    + " (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO=?)+1,"
			                    + " (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=?)+1,"
			                    + " ?, ?, ?, SYSDATE)";

	public List<MyAnswerDto> selectList();
	public MyAnswerDto selectOne(int boardno);
	public int insert(MyAnswerDto dto);
	public int update(MyAnswerDto dto);
	public int delete(int boardno);
	
	public int updateAnswer(int parentboardno);
	public int insertAnswer(MyAnswerDto dto);

}
