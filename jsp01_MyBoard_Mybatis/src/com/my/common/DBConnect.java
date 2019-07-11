package com.my.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("계정연결 성공");
		} catch (SQLException e) {
			System.out.println("계정연결 실패");
			e.printStackTrace();
		}

		return con;
	}
	
}
