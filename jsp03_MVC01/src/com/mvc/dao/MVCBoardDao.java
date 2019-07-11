package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCBoardDto;

import common.JDBCTemplate;

public class MVCBoardDao extends JDBCTemplate {

	public List<MVCBoardDto> selectList() {
		
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		
		String sql = "SELECT SEQ, WRITER, TITLE, REGDATE FROM MVCBOARD ORDER BY SEQ DESC";
		
		try {
			stmt = con.createStatement();
			System.out.println("query 준비 : " + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("query 실행 및 리턴");
			
			while(rs.next()) {
				MVCBoardDto dto = new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setRegdate(rs.getDate(4));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		
		return list;
	}
	
	public MVCBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MVCBoardDto dto = null;
		String sql = "SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MVCBOARD WHERE SEQ=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int insert(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO MVCBOARD VALUES (MVCBOARDSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			System.out.println("query 준비" + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}
	
	public int update(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE MVCBOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());
			System.out.println("query 준비 : " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return res;
	}
	
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "DELETE FROM MVCBOARD WHERE SEQ=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("query 준비 : " + sql);
			
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
