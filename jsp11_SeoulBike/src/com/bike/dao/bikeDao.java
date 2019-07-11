package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.dto.bikeDto;

import common.JDBCTemplate;

public class bikeDao extends JDBCTemplate {

	public int insert(List<bikeDto> list) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int[] res = null;
		int result = 0;
		String sql = "INSERT INTO BIKE_TB VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			for(bikeDto dto : list) {
				pstmt.setString(1, dto.getRent_id());
				pstmt.setString(2, dto.getAddr_gu());
				pstmt.setInt(3, dto.getContent_id());
				pstmt.setString(4, dto.getContent_nm());
				pstmt.setString(5, dto.getNew_addr());
				pstmt.setInt(6, dto.getCradle_count());
				pstmt.setDouble(7, dto.getLongitude());
				pstmt.setDouble(8, dto.getLatitude());
				pstmt.addBatch();
			}
			
			res = pstmt.executeBatch();
			// -2 : 성공 / -3 : 실패
			for(int i=0;i<res.length;i++) {
				if(res[i] == -2) {
					result++;
				}
			}
			if(result == list.size()) {
				commit(con);
				System.out.println("commit : " + result + "개");
			} else {
				rollback(con);
				System.out.println("rollback");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		
		return result;
	}
}
