package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.my.dto.MyBoardDto;

public class MyBoardDao {
	public List<MyBoardDto> selectList() {
		// 1. driver 계정 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. error");
			e.printStackTrace();
		}
		// 2. 계정 연결
		Connection con = null;
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. error");
			e.printStackTrace();
		}
		// 3. query 준비
		Statement stmt = null;
		ResultSet rs = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		String sql = "SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE " +
					"FROM MYBOARD " +
					"ORDER BY MYNO DESC";
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비 : " + sql);
			// 4. query 실행 및 리턴
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
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
			System.out.println("03. 04. error");
			e.printStackTrace();
		} finally {
			// 5. db종료
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public MyBoardDto selectOne(int myno) {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. error");
			e.printStackTrace();
		}
		// 2. 계정 연결
		Connection con = null;
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. error");
			e.printStackTrace();
		}
		// 3. query 준비
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD WHERE MYNO=?";
		MyBoardDto dto = new MyBoardDto();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dto;
	}
	
	public int Insert(MyBoardDto dto) {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. error");
			e.printStackTrace();
		}
		// 2. 계정 연결
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. error");
			e.printStackTrace();
		}
		// 3. query 준비
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,?,?,?,SYSDATE)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMyname());
			pstmt.setString(2, dto.getMytitle());
			pstmt.setString(3, dto.getMycontent());
			System.out.println("03. query 준비 : " + sql);
			
			// 4. query 실행 및 리턴
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 5. db 종료
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("05. db 종료");
		}	
		
		return res;
	}
	
	public int Update(MyBoardDto dto) {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. error");
			e.printStackTrace();
		}
		// 2. 계정 연결
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. error");
			e.printStackTrace();
		}
		// 3. query 준비
		String sql = "UPDATE MYBOARD SET MYTITLE=?, MYCONTENT=? WHERE MYNO=?";
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMytitle());
			pstmt.setString(2, dto.getMycontent());
			pstmt.setInt(3, dto.getMyno());
			System.out.println("03. query 준비 : " + sql);
			
			// 4. query 실행 및 리턴
			res = pstmt.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 5. db 종료
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return res;
	}
	
	public int Delete(int myno) {
		// 1. driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. error");
			e.printStackTrace();
		}
		// 2. 계정 연결
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. error");
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "DELETE FROM MYBOARD WHERE MYNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}














































