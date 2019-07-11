package com.login.biz;

import java.util.List;

import com.login.dao.MemberDao;
import com.login.dto.MemberDto;

public class MemberBiz {

	MemberDao dao = new MemberDao();
	
	public MemberDto login(String myid, String mypw) {
		return dao.login(myid, mypw);
	}
	
	public String idChk(String myid) {
		return dao.idChk(myid);
	}
	
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MemberDto dto) {
		return dao.update(dto);
	}
	
	public int deleteuser(int myno) {
		return dao.deleteuser(myno);
	}
	
	public List<MemberDto> userlistall() {
		return dao.userListAll();
	}
	
	public List<MemberDto> userlist() {
		return dao.userlist();
	}
	
	public MemberDto selectOne(int myno) {
		return dao.selectOne(myno);
	}
	
	public int updaterole(int myno, String role) {
		return dao.updaterole(myno, role);
	}
}
