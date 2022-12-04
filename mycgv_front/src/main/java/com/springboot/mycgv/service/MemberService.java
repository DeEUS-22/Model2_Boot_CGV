package com.springboot.mycgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberDao;
	
	
	/**
	 * 회원가입
	 */
	public int getJoin(MemberDto memberDto) {
		return memberDao.getJoin(memberDto);
	}
	
	/**
	 * 로그임
	 */
	public int getLogin(MemberDto memberDto) {
		return memberDao.getLogin(memberDto);
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
