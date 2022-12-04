package com.springboot.mycgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberDao;
	
	public int join(MemberDto memberDto) {
		return memberDao.join(memberDto);
	}
	
	public int login(MemberDto memberDto) {
		return memberDao.login(memberDto);
	}
	
	public List<MemberDto> getMemberList(PageDto pageDto){
		return memberDao.getMemberList(pageDto);
	}
	
	public MemberDto getMemberContent(String id) {
		return memberDao.getMemberContent(id);
	}
	
	public int idCheck(String id) {
		return memberDao.idCheck(id);
	}
}