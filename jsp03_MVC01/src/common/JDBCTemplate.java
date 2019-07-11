package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 연결 실패");
			e.printStackTrace();
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("계정 연결");
		} catch (SQLException e) {
			System.out.println("계정 연결 실패");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if(isConnection(con) || stmt != null || rs != null) {
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("db종료");
			} catch (SQLException e) {
				System.out.println("db종료 에러");
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection con, Statement stmt) {
		if(isConnection(con) || stmt != null) {
			try {
					stmt.close();
					con.close();
					System.out.println("db종료");
			} catch (SQLException e) {
				System.out.println("db종료 에러");
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnection(Connection con) {
		boolean chk = true;
		try {
			if(con.isClosed() || con == null) {
				chk = false;
			} else {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}
}
