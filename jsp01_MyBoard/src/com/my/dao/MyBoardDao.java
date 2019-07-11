package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.common.DBConnect;
import com.my.dto.MyBoardDto;

public class MyBoardDao {
	
	public List<MyBoardDto> selectList() {
		
		Connection con = DBConnect.getConnection();
		
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD";
		
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("query 준비" + sql);
			rs = pstmt.executeQuery();
			System.out.println("query 실행");
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
			System.out.println("DB종료");
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public MyBoardDto selectOne(int myno) {
		Connection con = DBConnect.getConnection();
		
		PreparedStatement pstmt = null;
		String sql = "SELECT MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD WHERE MYNO=?";
		ResultSet rs = null;
		MyBoardDto dto = new MyBoardDto();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
			System.out.println("query 준비 " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("query 실행");
			while(rs.next()) {
				dto.setMyname(rs.getString(1));
				dto.setMytitle(rs.getString(2));
				dto.setMycontent(rs.getString(3));
				dto.setMydate(rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("DB종료");
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int insert(MyBoardDto dto) {
		Connection con = DBConnect.getConnection();
		
		int res = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MYBOARD VALUES (MYSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMyname());
			pstmt.setString(2, dto.getMytitle());
			pstmt.setString(3, dto.getMycontent());
			System.out.println("query 준비 " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("DB종료");
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int update(MyBoardDto dto) {
		Connection con = DBConnect.getConnection();
		
		PreparedStatement pstmt = null;
		String sql = "UPDATE MYBOARD SET MYTITLE=?, MYCONTENT=?, MYDATE=SYSDATE WHERE MYNO=?";
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMytitle());
			pstmt.setString(2, dto.getMycontent());
			pstmt.setInt(3, dto.getMyno());
			System.out.println("query 준비 " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("DB종료");
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int delete(int myno) {
		Connection con = DBConnect.getConnection();
		
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM MYBOARD WHERE MYNO=?";
		int res = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
			System.out.println("query 준비 " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("query 실행");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("DB종료");
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
}














































