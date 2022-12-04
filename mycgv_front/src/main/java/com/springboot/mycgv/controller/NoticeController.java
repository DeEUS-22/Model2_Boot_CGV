package com.springboot.mycgv.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springboot.mycgv.dto.NoticeDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.service.NoticeService;
import com.springboot.mycgv.service.PageService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private PageService pageService;
	
	
	@GetMapping("/notice_list_json/{rpage}")
	public String notice_list_json(@PathVariable String rpage, Model model) {
		PageDto pageDto = pageService.getPageCount(rpage, "notice");
		ArrayList<NoticeDto> list = (ArrayList<NoticeDto>) noticeService.noticeList(pageDto);
		
		JsonObject jobject = new JsonObject();
		JsonArray jarray = new JsonArray();
		Gson gson = new Gson();
		
		for(NoticeDto noticeDto : list) {
			JsonObject jo = new JsonObject();
			jo.addProperty("rno", noticeDto.getRno());
			jo.addProperty("nid", noticeDto.getNid());
			jo.addProperty("ntitle", noticeDto.getNtitle());
			jo.addProperty("nhits", noticeDto.getNhits());
			jo.addProperty("ndate", noticeDto.getNdate());
			
			jarray.add(jo);
		}
		
		jobject.add("list", jarray);
		jobject.addProperty("reqPage", pageDto.getReqPage());
		jobject.addProperty("pageSize", pageDto.getPageSize());
		jobject.addProperty("dbCount", pageDto.getDbCount());
		
		return gson.toJson(jobject);
	}
	
	@GetMapping("/notice_content_json/{nid}")
	public String notice_content_json(@PathVariable String nid, Model model) {
		NoticeDto noticeDto = noticeService.noticeContent(nid);
		noticeService.updateHits(nid);
		
		JsonObject jobject = new JsonObject();
		Gson gson = new Gson();
		
		jobject.addProperty("nid", noticeDto.getNid());
		jobject.addProperty("ntitle", noticeDto.getNtitle());
		jobject.addProperty("ncontent", noticeDto.getNcontent());
		jobject.addProperty("nhits", noticeDto.getNhits());
		jobject.addProperty("nfile", noticeDto.getNfile());
		jobject.addProperty("nsfile", noticeDto.getNsfile());
		jobject.addProperty("ndate", noticeDto.getNdate());
		
		return gson.toJson(jobject);
	}
}
