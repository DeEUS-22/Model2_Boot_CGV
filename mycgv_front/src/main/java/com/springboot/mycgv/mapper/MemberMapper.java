package com.springboot.mycgv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.PageDto;

@Mapper
public interface MemberMapper {
	int getJoin(MemberDto memberDto);
	int getLogin(MemberDto memberDto);
	List<MemberDto> getMemberList(PageDto pageDto);
	MemberDto getMemberContent(String id);
	String getMid();
	int idCheck(String id);
}
