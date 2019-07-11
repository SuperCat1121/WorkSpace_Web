package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCBoardDto;

import common.JDBCTemplate;

public class MVCBoardDaoImpl extends JDBCTemplate implements MVCBoardDao {

	@Override
	public List<MVCBoardDto> selectList() {
		Connection con = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		
		try {
			pstmt = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("query 준비 : " + SELECT_LIST_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MVCBoardDto dto = new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
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
	public MVCBoardDto selectone(int seq) {
		Connection con = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		MVCBoardDto dto = new MVCBoardDto();
		
		try {
			pstmt = con.prepareStatement(SELECT_ONE_SQL);
			pstmt.setInt(1, seq);
			System.out.println("query 준비 : " + SELECT_ONE_SQL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return dto;
	}

	@Override
	public int insert(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
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
	public int update(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());
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
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, seq);
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

}
