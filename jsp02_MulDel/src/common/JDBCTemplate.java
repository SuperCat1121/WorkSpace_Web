package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	// 1. driver 연결
	// 2. 계정 연결
	// 5. db 종료
	
	public static Connection getConnection() {
		// driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 연결 실패...");
			e.printStackTrace();
		}
		// 계정 연결
		Connection con = null;
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("계정 연결 성공");
		} catch (SQLException e) {
			System.out.println("계정 연결 실패...");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static boolean isConnection(Connection con) {
		boolean valid = true;
		
		try {
			if(con == null || con.isClosed()) {
				valid = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return valid;
	}
	
	public static void close(Connection con) {
		if(isConnection(con)) { // null pointer exception 방지 / 500에러의 주 원인 : null
			try {
				con.close();
				System.out.println("Con 종료");
			} catch (SQLException e) {
				System.out.println("Con 종료 실패");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
				System.out.println("ResultSet 종료");
			} catch (SQLException e) {
				System.out.println("ResultSet 종료 실패");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
				System.out.println("stmt 종료");
			} catch (SQLException e) {
				System.out.println("stmt 종료 실패");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			rs.close();
			System.out.println("ResultSet 종료");
			stmt.close();
			System.out.println("stmt 종료");
			con.close();
			System.out.println("Con 종료");
		} catch (SQLException e) {
			System.out.println("db 종료 실패");
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt, Connection con) {
		try {
			stmt.close();
			System.out.println("stmt 종료");
			con.close();
			System.out.println("Con 종료");
		} catch (SQLException e) {
			System.out.println("db 종료 실패");
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		if(isConnection(con)) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection con) {
		if(isConnection(con)) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}








