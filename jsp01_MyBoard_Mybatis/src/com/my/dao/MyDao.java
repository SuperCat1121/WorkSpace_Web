package com.my.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.my.dto.MyBoardDto;

public class MyDao extends SqlMapConfig {

	private String namespace = "com.my.db.mapper.";
	
	public List<MyBoardDto> selectList() {
		SqlSession session = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		session = getSqlSessionFactory().openSession();
		list = session.selectList(namespace+"selectList");
		
		return list;
	}
	
	public MyBoardDto selectone(int myno) {
		SqlSession session = null;
		MyBoardDto dto = new MyBoardDto();
		
		session = getSqlSessionFactory().openSession();
		dto = session.selectOne(namespace + "selectOne", myno);
		
		session.close();
		
		return dto;
	}
	
	public int insert(MyBoardDto dto) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(namespace + "insert", dto);
		
		session.close();
		
		return res;
	}
	
	public int update(MyBoardDto dto) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.update(namespace + "update", dto);
		
		session.close();
		
		return res;
	}
	
	public int delete(int myno) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		res = session.update(namespace + "delete", myno);
		
		session.close();
		
		return res;
	}

}




























