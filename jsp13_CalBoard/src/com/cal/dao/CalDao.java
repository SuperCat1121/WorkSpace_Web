package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

import common.JDBCTemplate;

public class CalDao extends JDBCTemplate {
	
	public List<CalDto> dtoList(String id, String mdate) {
		Connection con = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = "SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE FROM CALBOARD"
				+ " WHERE ID=? AND SUBSTR(MDATE,1,8)=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, mdate);
			System.out.println("query 준비 : " + sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				
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

	public int insertCalBoard(CalDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO CALBOARD VALUES (CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getMdate());
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
	
	public List<CalDto> getCalViewList(String id, String yyyyMM) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = " SELECT *" + 
				     " FROM (SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN," + 
				     "	            SEQ, ID, TITLE, CONTENT, MDATE, REGDATE" + 
				     "	    FROM CALBOARD" + 
				     "	    WHERE ID = ? AND SUBSTR(MDATE,1,6)=?)" + 
				     " WHERE RN BETWEEN 1 AND 3";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, yyyyMM);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setMdate(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				
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
	
	public int getCalViewCount(String id, String yyyyMMdd) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "SELECT COUNT(*) FROM CALBOARD WHERE ID=? AND SUBSTR(MDATE,1,8)=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, yyyyMMdd);
			System.out.println("quert 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return cnt;
	}
}



























