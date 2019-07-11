package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

import common.JDBCTemplate;

public class MyBoardDao extends JDBCTemplate{

	public List<MyBoardDto> selectList(int startcount, int endcount) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		String sql = "SELECT B.RNUM, B.MYNAME, B.MYTITLE, B.MYCONTENT, B.MYDATE"
				+ " FROM (SELECT ROWNUM AS RNUM, A.MYNAME, A.MYTITLE, A.MYCONTENT, A.MYDATE"
				+ "       FROM (SELECT MYNAME, MYTITLE, MYCONTENT, MYDATE"
				+ "              FROM MYBOARD ORDER BY MYNO) A"
				+ "        WHERE ROWNUM <= ?) B"
				+ " WHERE B.RNUM >= ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endcount);
			pstmt.setInt(2, startcount);
			System.out.println("query 준비 : " + sql);
			rs = pstmt.executeQuery();
			System.out.println("query 실행 및 리턴");
			
			while(rs.next()) {
				MyBoardDto dto = new MyBoardDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public MyBoardDto selectone(int myno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD WHERE MYNO=?";
		
		MyBoardDto dto = new MyBoardDto();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setMyno(myno);
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return dto;
	}
	
	public int insert(MyBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO MYBOARD VALUES (MYSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMyname());
			pstmt.setString(2, dto.getMytitle());
			pstmt.setString(3, dto.getMycontent());
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
	
	public int update(MyBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE MYBOARD SET MYTITLE=?, MYCONTENT=?, MYDATE=SYSDATE WHERE MYNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMytitle());
			pstmt.setString(2, dto.getMycontent());
			pstmt.setInt(3, dto.getMyno());
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
	
	public int delete(int myno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "DELETE FROM MYBOARD WHERE MYNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
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
	
	public int totalpage() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = 0;
		String sql = "SELECT COUNT(*) FROM MYBOARD";
		
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
			System.out.println("query 실행 및 리턴");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return res;
	}
}




























