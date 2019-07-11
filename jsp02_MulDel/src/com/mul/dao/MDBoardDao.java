package com.mul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mul.dto.MDBoardDto;

import static common.JDBCTemplate.*;

public class MDBoardDao {

	public List<MDBoardDto> selectList() {
		Connection con = getConnection();
		
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		String sql = "SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD ORDER BY SEQ DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("query 실행 및 리턴");
			while(rs.next()) {
				MDBoardDto dto = new MDBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		
		MDBoardDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD WHERE SEQ=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			System.out.println("query 준비 : " + sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MDBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return dto;
	}
	public boolean insert(MDBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO MDBOARD VALUES (MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			
			res = pstmt.executeUpdate();
			if(res > 0) {
				commit(con);
				System.out.println("커밋 성공");
			} else {
				rollback(con);
				System.out.println("롤백됨, 커밋 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, con);
		}
		
		return (res > 0)?true:false;
	}
	public boolean update(MDBoardDto dto) {
		Connection con = getConnection();
		
		String sql = "UPDATE MDBOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
		int res = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());
			
			res = pstmt.executeUpdate();
			if(res > 0) {
				commit(con);
				System.out.println("커밋 성공");
			} else {
				rollback(con);
				System.out.println("롤백됨, 커밋 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (res > 0)?true:false;
	}
	public boolean delete(int seq) {
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM MDBOARD WHERE SEQ=?";
		int res = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			res = pstmt.executeUpdate();
			if(res > 0) {
				commit(con);
				System.out.println("커밋 성공");
			} else {
				rollback(con);
				System.out.println("롤백, 커밋 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, con);
		}
		
		return (res > 0)?true:false;
	}
	public boolean multidelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "DELETE FROM MDBOARD WHERE SEQ=?";
		int[] cnt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			for(int i=0;i<seq.length;i++) {
				pstmt.setString(1, seq[i]);
				// addBatch() : 메모리에 적재 후, executeBatch()가 호출될 때 한번에 실행
				pstmt.addBatch();
				System.out.println("query 준비 : " + sql + seq[i]);
			}
			cnt = pstmt.executeBatch();
			System.out.println("query 실행 및 리턴");
			
			// -2 : 성공, -3 : 실패
			for(int i=0;i<cnt.length;i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(res == seq.length) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("query 준비, 실행 및 리턴 에러");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
			System.out.println("db 종료");
		}
		
		return (res == seq.length)?true:false;
	}
}
