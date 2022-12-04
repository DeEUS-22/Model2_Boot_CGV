package com.springboot.mycgv.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.service.BoardService;
import com.springboot.mycgv.service.FileService;
import com.springboot.mycgv.service.PageService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private FileService fileService;
	
	
	/**
	 * board_write.do : 게시판 글쓰기 화면
	 */
	@GetMapping("/board_write")
	public String board_write_get() {
		return "/board/board_write";
	}
	
	@PostMapping("/board_write")
	public String board_write_post(BoardDto boardDto) throws Exception{
		
		if(fileService.fileCheck(boardDto.getFile1())) {
			boardDto = (BoardDto)fileService.init(boardDto, "board");
			
			int result = boardService.getWrite(boardDto);
			if(result == 1) fileService.saveFile(boardDto, "board");
			
		}else {
			boardDto.setBfile("");
			boardDto.setBsfile("");	
			boardService.getWrite(boardDto);
		}
		
		return "redirect:/board_list/1";
	}
	
	/**
	 * board_delete.do : 게시판 삭제 화면
	 */
	@GetMapping("/board_delete/{bid}/{rpage}")
	public String board_delete(@PathVariable String bid, @PathVariable String rpage, Model model) {

		model.addAttribute("bid", bid);
		model.addAttribute("rpage", rpage);
		model.addAttribute("bsfile", boardService.getContent(bid).getBsfile());
		
		return "/board/board_delete";
	}
	
	@PostMapping("/board_delete")
	public String board_delete_post(BoardDto boardDto) throws Exception{
		
		int result = boardService.getDelete(boardDto);
		if(result == 1) {
			fileService.deleteFile(boardDto.getBsfile());
		}
		
		return "redirect:/board_list/1";
		
	}
	
	/**
	 * board_update.do : 게시판 수정 화면
	 */
	@GetMapping("/board_update/{bid}/{rpage}")
	public String board_update(@PathVariable String bid, @PathVariable String rpage, Model model) {
		
		model.addAttribute("vo", boardService.getContent(bid));
		model.addAttribute("rpage", rpage);
		
		return "/board/board_update";
	}
	
	@PostMapping("/board_update")
	public String board_update_post(BoardDto boardDto) throws Exception{
		
		if(fileService.fileCheck(boardDto.getFile1())) {
			
			String oldfile = boardDto.getBsfile();
			boardDto = (BoardDto)fileService.init(boardDto, "board");
			int result = boardService.getUpdate(boardDto);
			if(result == 1) {
				fileService.saveFile(boardDto, "board");
				fileService.deleteFile(oldfile);
			}
			
		}else {
			boardService.getUpdate(boardDto);
		}
		
		return "redirect:/board_list";
		
	}
	
	/**
	 * board_content.do : 게시판 상세 정보
	 */
	@GetMapping("/board_content/{bid}/{rpage}")
	public String board_content(@PathVariable String bid, @PathVariable String rpage, Model model) {

		model.addAttribute("vo", boardService.getContent(bid));
		model.addAttribute("rpage", rpage);
		boardService.getUpdateHits(bid);
		
		return "/board/board_content";
	}
	
	/**
	 * board_list.do : 게시판 전체 리스트 
	 */
	@GetMapping("/board_list/{rpage}")
	public String board_list(@PathVariable String rpage, Model model) {
		PageDto pageDto = pageService.getPageCount(rpage, "board");
				
		model.addAttribute("list", boardService.getList(pageDto));
		model.addAttribute("page", pageDto);
		
		return "/board/board_list";
	}
	
	
	@GetMapping("/board_list")
	public String board_list_root() {
		return "redirect:/board_list/1" ;
	}
	
	
	
}
