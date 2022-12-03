package com.springboot.mycgv.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mycgv.dto.BoardDto;


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
	public Object init(Object object, String objName) {//이렇게 적으면 object의 자식들은 다 들어갈 수 있다. 위에 3개 다 파라미터로 들어올 수 있다.
		Object objectDto = null;
	
		if(objName.equals("board")) {
			BoardDto dto = (BoardDto)object; //자기 모습으로 형변환을 해야해서 적어준 코드. 
			MultipartFile file = dto.getFile1();
			
			if(file != null) {
				if(!file.getOriginalFilename().equals("")) {
					UUID uuid = UUID.randomUUID();
					dto.setBfile(file.getOriginalFilename());
					dto.setBsfile(uuid+"_"+file.getOriginalFilename());			
				}
			}
			objectDto = dto;
		}//else if(objName.equals("notice")) { notice 내용이 없어서 에러나서 주석처리한거다.
		//	NoticeDto dto = (NoticeDto)object;
		//	MultipartFile file = dto.getFile1();
			
		//	if(file != null) {
		//		if(!file.getOriginalFilename().equals("")) {
		//			UUID uuid = UUID.randomUUID();
		//			dto.setBfile(file.getOriginalFilename());
		//			dto.setBsfile(uuid+"_"+file.getOriginalFilename());			
		//		}
		//	}
		
		return objectDto;
		} //이렇게 사용하면 된다.
	
/*
 * 위 방식대로 수정하기 전 버전이 바로 아래 적혀있는 내용이다.	
	public BoardDto init(BoardDto dto) {
		MultipartFile file = dto.getFile1();
		
		if(file != null) {
			if(!file.getOriginalFilename().equals("")) {
				UUID uuid = UUID.randomUUID();
				dto.setBfile(file.getOriginalFilename());
				dto.setBsfile(uuid+"_"+file.getOriginalFilename());			
			}
		}
		
		return dto;
	}
*/	
	
	public void saveFile(BoardDto dto) throws Exception {
		File file = new File(uploadPath + dto.getBsfile());
		dto.getFile1().transferTo(file);
	}	
	
	
	public void deleteFile(String bsfile) throws Exception{
		File file = new File(uploadPath + bsfile);
		if(file.exists()) {
			file.delete();
		}
	}
	
}
