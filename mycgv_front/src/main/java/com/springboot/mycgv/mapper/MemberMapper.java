package com.springboot.mycgv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.dto.SessionDto;

@Mapper
public interface MemberMapper {

	int join(MemberDto memberDto);
	int login(MemberDto memberDto);
	List<MemberDto> getMemberList(PageDto pageDto);
	MemberDto getMemberContent(String id);
	String getMid();
	int idCheck(String id);
	
}
