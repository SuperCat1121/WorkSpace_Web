package com.answer.dao;

import java.util.List;

import com.answer.dto.answerDto;
import com.member.dto.memberDto;

public interface answerDao {

	String SELECT_LIST_SQL = "SELECT BOARDNO, GROUPNO, GROUPSQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE"
			                  + " FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ";
	String SELECT_ONE_SQL = "SELECT BOARDNO, GROUPNO, GROUPSQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE"
                             + " FROM ANSWERBOARD WHERE BOARDNO=?";
	String INSERT_SQL = "INSERT INTO ANSWERBOARD VALUES (BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL,"
			             + " 1, 0, 'N', ?, ?, ?, SYSDATE)";
	String UPDATE_SQL = "UPDATE ANSWERBOARD SET TITLE=?, CONTENT=? WHERE BOARDNO=?";
	String DELETE_SQL = "UPDATE ANSWERBOARD SET DELFLAG='Y' WHERE BOARDNO=?";
	
	String ANSWER_UPDATE_FOR_INSERT = "UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ+1"
			                         + " WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE GROUPNO=?)"
			                         + "       AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE GROUPSQ=?)";
	String ANSWER_INSERT_SQL = "INSERT INTO ANSWERBOARD VALUES (BOARDNOSEQ.NEXTVAL,"
			                   + " (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?),"
			                   + " (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO=?)+1,"
			                   + " (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=?)+1,"
			                   + " 'N', ?, ?, ?, SYSDATE)";
	String LOGIN_SQL = "SELECT MEMBERNO, MEMBERID, MEMBERNAME, MEMBERPHONE, MEMBERROLE"
			         + " FROM MEMBERBOARD WHERE MEMBERID=? AND MEMBERPW=? AND MEMBERENABLED='Y'";
	String DELETEUSER_SQL = "UPDATE MEMBERBOARD SET MEMBERENABLED='N' WHERE MEMBERNO=?";
	
	public List<answerDto> selectlist();
	public answerDto selectone(int boardno);
	public int insert(answerDto dto);
	public int update(answerDto dto);
	public int delete(int boardno);
	
	public int answerupdateforinsert(int parentboardno);
	public int answerinsert(answerDto dto);
	
	public memberDto login(String id, String pw);
	public int deleteuser(int memberno);
}