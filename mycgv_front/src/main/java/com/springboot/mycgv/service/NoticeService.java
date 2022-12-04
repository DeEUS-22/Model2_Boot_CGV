package com.springboot.mycgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mycgv.dto.NoticeDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.mapper.NoticeMapper;

@Service
public class NoticeService {
	@Autowired
	private NoticeMapper noticeDao;
	
	
	public List<NoticeDto> noticeList(PageDto pageDto){
		return noticeDao.noticeList(pageDto);
	}
	
	public NoticeDto noticeContent(String nid) {
		return noticeDao.noticeContent(nid);
	}
	
	public int noticeWrite(NoticeDto noticeDto) {
		return noticeDao.noticeWrite(noticeDto);
	}
	
	public int noticeUpdate(NoticeDto noticeDto) {
		return noticeDao.noticeUpdate(noticeDto);
	}
	
	public int noticeDelete(NoticeDto noticeDto) {
		return noticeDao.noticeDelete(noticeDto);
	}
	
	public int updateHits(String nid) {
		return noticeDao.updateHits(nid);
	}
	
}
