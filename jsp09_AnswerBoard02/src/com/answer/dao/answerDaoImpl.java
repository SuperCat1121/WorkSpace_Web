package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.answerDto;
import com.member.dto.memberDto;

import common.JDBCTemplate;

public class answerDaoImpl extends JDBCTemplate implements answerDao {

	@Override
	public List<answerDto> selectlist() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<answerDto> list = new ArrayList<answerDto>();
		
		try {
			pstmt = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("query 준비 : " + SELECT_LIST_SQL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				answerDto dto = new answerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupsq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setDelflag(rs.getString(5));
				dto.setTitle(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setWriter(rs.getString(8));
				dto.setRegdate(rs.getDate(9));
				
				list.add(dto);
			}
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public answerDto selectone(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		answerDto dto = new answerDto();
		
		try {
			pstmt = con.prepareStatement(SELECT_ONE_SQL);
			pstmt.setInt(1, boardno);
			System.out.println("query 준비 : " + SELECT_ONE_SQL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupsq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setDelflag(rs.getString(5));
				dto.setTitle(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setWriter(rs.getString(8));
				dto.setRegdate(rs.getDate(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return dto;
	}

	@Override
	public int insert(answerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			System.out.println("query 준비 : " + INSERT_SQL);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}

	@Override
	public int update(answerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getBoardno());
			System.out.println("query 준비 : " + UPDATE_SQL);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}

	@Override
	public int delete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, boardno);
			System.out.println("query 준비 : " + DELETE_SQL);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}

	@Override
	public int answerupdateforinsert(int parentboardno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(ANSWER_UPDATE_FOR_INSERT);
			pstmt.setInt(1, parentboardno);
			pstmt.setInt(2, parentboardno);
			System.out.println("query 준비 : " + ANSWER_UPDATE_FOR_INSERT);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}

	@Override
	public int answerinsert(answerDto dto) { // ** 이 dto 의 boardno 는 부모 글의 글번호.
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(ANSWER_INSERT_SQL);
			pstmt.setInt(1, dto.getBoardno());
			pstmt.setInt(2, dto.getBoardno());
			pstmt.setInt(3, dto.getBoardno());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getWriter());
			System.out.println("query 준비 : " + ANSWER_INSERT_SQL);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}

	@Override
	public memberDto login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberDto dto = new memberDto();
		
		try {
			pstmt = con.prepareStatement(LOGIN_SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			System.out.println("query 준비 : " + LOGIN_SQL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setMemberno(rs.getInt(1));
				dto.setMemberid(rs.getString(2));
				dto.setMembername(rs.getString(3));
				dto.setMemberphone(rs.getString(4));
				dto.setMemberrole(rs.getString(5));
			}
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return dto;
	}
	
	@Override
	public int deleteuser(int memberno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(DELETEUSER_SQL);
			pstmt.setInt(1, memberno);
			System.out.println("query 준비 : " + DELETEUSER_SQL);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}
	
}
