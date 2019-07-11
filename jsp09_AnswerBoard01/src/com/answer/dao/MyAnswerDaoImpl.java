package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.MyAnswerDto;

import common.JDBCTemplate;

public class MyAnswerDaoImpl extends JDBCTemplate implements MyAnswerDao {

	@Override
	public List<MyAnswerDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyAnswerDto> list = new ArrayList<MyAnswerDto>();
		
		try {
			pstmt = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("query 준비 : " + SELECT_LIST_SQL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MyAnswerDto dto = new MyAnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupsq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public MyAnswerDto selectOne(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyAnswerDto dto = new MyAnswerDto();
		
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
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
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
	public int insert(MyAnswerDto dto) {
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
	public int update(MyAnswerDto dto) {
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
	public int updateAnswer(int parentboardno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(UPDATE_ANSWER_SQL);
			pstmt.setInt(1, parentboardno);
			pstmt.setInt(2, parentboardno);
			System.out.println("query 준비 : " + UPDATE_ANSWER_SQL);
			
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
	public int insertAnswer(MyAnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(INSERT_ANSWER_SQL);
			pstmt.setInt(1, dto.getBoardno());
			pstmt.setInt(2, dto.getBoardno());
			pstmt.setInt(3, dto.getBoardno());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getWriter());
			System.out.println("query 준비 : " + INSERT_ANSWER_SQL);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
