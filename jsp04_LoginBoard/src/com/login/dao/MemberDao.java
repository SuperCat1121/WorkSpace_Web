package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.biz.MemberBiz;
import com.login.dto.MemberDto;

import common.JDBCTemplate;

public class MemberDao extends JDBCTemplate {

	/*
		관리자 기능
		1. 회원 전체 정보(탈퇴한 회원 포함)
		2. 가입된 회원의 전체 정보(myenabled='Y')
		3. 회원 등급 조정
	*/
	
	/*
		유저 기능
		1. 로그인
		2. 회원가입 -> 아이디 중복체크
		3. 정보 조회
		4. 정보 수정(주소, 비밀번호, 전화번호, 이메일 만)
		5. 회원 탈퇴(UPDATE MYENABLED='N')
	*/
	public MemberDto login(String myid, String mypw) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto res = new MemberDto();
		String sql = "SELECT * FROM MEMBERBOARD WHERE MYID=? AND MYPW=? AND MYENABLED=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myid);
			pstmt.setString(2, mypw);
			pstmt.setString(3, "Y");
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("query 실행 및 리턴");
			while(rs.next()) {
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return res;
	}
	
	public String idChk(String myid) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MYID FROM MEMBERBOARD WHERE MYID=?";
		String res = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myid);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("query 실행 및 리턴");
			while(rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return res;
	}
	
	public int insert(MemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO MEMBERBOARD VALUES (MEMBERBOARDSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER')";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMyid());
			pstmt.setString(2, dto.getMypw());
			pstmt.setString(3, dto.getMyname());
			pstmt.setString(4, dto.getMyaddr());
			pstmt.setString(5, dto.getMyphone());
			pstmt.setString(6, dto.getMyemail());
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
	
	public int update(MemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE MEMBERBOARD SET MYPW=?, MYADDR=?, MYPHONE=?, MYEMAIL=? WHERE MYNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMypw());
			pstmt.setString(2, dto.getMyaddr());
			pstmt.setString(3, dto.getMyphone());
			pstmt.setString(4, dto.getMyemail());
			pstmt.setInt(5, dto.getMyno());
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
	
	public int deleteuser(int myno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE MEMBERBOARD SET MYENABLED='N' WHERE MYNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
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
// -------------------------------------- 관리자 처리 --------------------------------------
	public List<MemberDto> userListAll() {
		Connection con = getConnection();
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		PreparedStatement pstmt = null;
		String sql = "SELECT MYNO, MYID, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE FROM MEMBERBOARD"
				+ " ORDER BY MYNO DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMyname(rs.getString(3));
				dto.setMyaddr(rs.getString(4));
				dto.setMyphone(rs.getString(5));
				dto.setMyemail(rs.getString(6));
				dto.setMyenabled(rs.getString(7));
				dto.setMyrole(rs.getString(8));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public List<MemberDto> userlist() {
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		ResultSet rs = null;
		
		String sql = "SELECT MYNO, MYID, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE FROM MEMBERBOARD"
				+ " WHERE MYENABLED='Y' ORDER BY MYNO DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("query 준비 : " + sql);
			rs = pstmt.executeQuery();
			System.out.println("query 실행 및 리턴");
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMyname(rs.getString(3));
				dto.setMyaddr(rs.getString(4));
				dto.setMyphone(rs.getString(5));
				dto.setMyemail(rs.getString(6));
				dto.setMyenabled(rs.getString(7));
				dto.setMyrole(rs.getString(8));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int updaterole(int myno, String myrole) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE MEMBERBOARD SET MYROLE=? WHERE MYNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, myrole);
			pstmt.setInt(2, myno);
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
	
	public MemberDto selectOne(int myno) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MYNO, MYID, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE"
				+ " FROM MEMBERBOARD WHERE MYNO=?";
		MemberDto dto = new MemberDto();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, myno);
			System.out.println("query 준비 : " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("query 실행 및 리턴");
			
			while(rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMyname(rs.getString(3));
				dto.setMyaddr(rs.getString(4));
				dto.setMyphone(rs.getString(5));
				dto.setMyemail(rs.getString(6));
				dto.setMyenabled(rs.getString(7));
				dto.setMyrole(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		
		return dto;
	}
}






























































