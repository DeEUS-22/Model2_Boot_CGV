package com.springboot.mycgv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.mycgv.dto.NoticeDto;
import com.springboot.mycgv.dto.PageDto;

@Mapper
public interface NoticeMapper {
	
	List<NoticeDto> noticeList(PageDto pageDto);
	NoticeDto noticeContent(String nid);
	int noticeWrite(NoticeDto noticeDto);
	int noticeUpdate(NoticeDto noticeDto);
	int noticeDelete(NoticeDto noticeDto);
	int updateHits(String nid);
	

}
