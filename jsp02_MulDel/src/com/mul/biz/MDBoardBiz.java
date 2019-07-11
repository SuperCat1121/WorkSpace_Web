package com.mul.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mul.dao.MDBoardDao;
import com.mul.dto.MDBoardDto;

import common.JDBCTemplate;

public class MDBoardBiz {
	
	MDBoardDao dao;
	public MDBoardBiz() {
		dao = new MDBoardDao();
	}

	public List<MDBoardDto> selectList() {
		return dao.selectList();
	}
	public MDBoardDto selectOne(int seq) {
		return dao.selectOne(seq);
	}
	public boolean insert(MDBoardDto dto) {
		return dao.insert(dto);
	}
	public boolean update(MDBoardDto dto) {
		return dao.update(dto);
	}
	public boolean delete(int seq) {
		return dao.delete(seq);
	}
	public boolean multidelete(String[] seq) {
		return dao.multidelete(seq);
	}

}
