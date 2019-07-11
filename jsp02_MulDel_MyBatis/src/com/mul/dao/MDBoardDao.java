package com.mul.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mul.dto.MDBoardDto;

public class MDBoardDao extends SqlMapConfig {

	private String namespace = "muldelmapper.";
	
	public List<MDBoardDto> selectList() {
		SqlSession session = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace + "selectList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public MDBoardDto selectOne(int seq) {
		return null;
	}
	public boolean insert(MDBoardDto dto) {		
		return false;
	}
	public boolean update(MDBoardDto dto) {
		return false;
	}
	public boolean delete(int seq) {
		return false;
	}
	public boolean multidelete(String[] seq) {
		int count = 0;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seq);
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			count = session.delete(namespace + "muldel", map);
			
			if(count == seq.length) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return (count==seq.length)?true:false;
	}
	
	public List<MDBoardDto> search(String searchoption, String searchtext) {
		SqlSession session = null;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchoption", searchoption);
		map.put("searchtext", searchtext);
		
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		System.out.println(map.get("searchoption"));
		System.out.println(map.get("searchtext"));
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace + "search", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}



















