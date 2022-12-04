package com.springboot.mycgv.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.MovieDto;
import com.springboot.mycgv.dto.NoticeDto;


@Service
public class FileService {
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	public boolean fileCheck(MultipartFile file) {
		if(file.getOriginalFilename().equals("")) {
			return false;
		}else {
			return true;
		}		
	}
	
	/**
	 * BoardDto, NoticeDto, MemberDto 객체의 공통적인 부모 찾기 ----> Object
	 */
	public Object init(Object object, String objName) {
		
		Object objDto = null;
		
		if(objName.equals("board")) {
			BoardDto dto = (BoardDto)object;
			
			MultipartFile file = dto.getFile1();
			
			if(file != null) {
				if(!file.getOriginalFilename().equals("")) {
					UUID uuid = UUID.randomUUID();
					dto.setBfile(file.getOriginalFilename());
					dto.setBsfile(uuid+"_"+file.getOriginalFilename());
				}
			}
			objDto = dto;
			
		}else if(objName.equals("notice")) {
			NoticeDto dto = (NoticeDto)object;
			
			MultipartFile file = dto.getFile1();
			
			if(file != null) {
				if(!file.getOriginalFilename().equals("")) {
					UUID uuid = UUID.randomUUID();
					dto.setNfile(file.getOriginalFilename());
					dto.setNsfile(uuid+"_"+file.getOriginalFilename());
				}
			}
			objDto = dto;
		}else if(objName.equals("movie")) {
			MovieDto dto = (MovieDto)object;
			
			MultipartFile[] files = dto.getFiles();
			
			for(MultipartFile file : files ) {
				if(file != null) {
					if(!file.getOriginalFilename().equals("")) {
						UUID uuid = UUID.randomUUID();
						dto.setMfile1(file.getOriginalFilename());
						dto.setMsfile1(uuid+"_"+file.getOriginalFilename());
						dto.setMfile2(file.getOriginalFilename());
						dto.setMsfile2(uuid+"_"+file.getOriginalFilename());
					}
				}
			}
			objDto = dto;
			
		}
		

		return objDto;
	}
	
	public void saveFile(Object object, String objName) throws Exception {
		
		Object objDto = null;
		
		if(objName.equals("board")) {
			BoardDto dto = (BoardDto)object;
			File file = new File(uploadPath + dto.getBsfile());
			dto.getFile1().transferTo(file);
			
			objDto = dto;
		}else if(objName.equals("notice")) {
			NoticeDto dto = (NoticeDto)object;
			File file = new File(uploadPath + dto.getNsfile());
			dto.getFile1().transferTo(file);
	
			objDto = dto;
		}
		
	}
	
	
	public void deleteFile(String bsfile) throws Exception{
		File file = new File(uploadPath + bsfile);
		if(file.exists()) {
			file.delete();
		}
	}
	
}
