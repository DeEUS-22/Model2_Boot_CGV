package com.springboot.mycgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.PageDto;
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
	 * board_delete_check.do : 게시판 삭제 처리
	 */
/*	@RequestMapping(value="/board_delete_check.do", method=RequestMethod.POST)
	public ModelAndView board_delete_check(String bid, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		//삭제할 bid 행에 bsfile의 이름을 가져오기 위해 dao.select(bid) 메소드 호출--> upload폴더에 파일 유무 확인
		CgvBoardVO vo = boardService.getContent(bid); //dao.select(bid) 메소드는 delete 메소드 호출 전에 실행되어야함!! 
		int result = boardService.getDelete(bid);
		
		if(result == 1){
			//게시글 삭제시 upload 폴더에 존재하는 파일이 있다면 삭제하기
			fileService.fileDelete(vo, request);
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}*/
	
	/**
	 * board_update_check.do : 게시판 수정 처리
	 */
/*	@RequestMapping(value="/board_update_check.do", method=RequestMethod.POST)
	public ModelAndView board_update_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//기존 파일이 존재하는 경우 이름을 변수로 저장
		String old_filename = vo.getBsfile();
		
		//수정시 새로운 파일을 선택했는지 확인
		vo = fileService.update_fileCheck(vo); 
		int result = boardService.getUpdate(vo);
		
		if(result == 1){
			//새로운 파일을 upload 폴더에 저장
			fileService.update_filesave(vo, request, old_filename);
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}*/
	
	
	
	/**
	 * board_write_check.do : 게시판 글쓰기 처리
	 */
/*	@RequestMapping(value="/board_write_check.do", method=RequestMethod.POST)
	public ModelAndView board_write_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//1. 파일체크 후 bfile, bsfile에 저장되는 이름 생성
		vo = fileService.fileCheck(vo);
		int result = boardService.getWriteResult(vo);
		
		if(result == 1){
			
			//2. upload 폴더에 bsfile 명으로 실제 파일 업로드 처리
			fileService.fileSave(vo, request);
			
			//mv.setViewName("/board/board_list"); //에러X, 아무런 게시글 출력되지 X
			mv.setViewName("redirect:/board_list.do"); //DB연동을 Controller에서 진행하므로, 새로운 연결을 수행!!
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}*/
	
	/**
	 * board_write : 게시판 글쓰기 화면
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
	 * board_delete : 게시판 삭제 화면
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
	 * board_update : 게시판 수정 화면
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
	 * board_content : 게시판 상세 정보
	 */
	@GetMapping("/board_content/{bid}/{rpage}")
	public String board_content(@PathVariable String bid, @PathVariable String rpage, Model model) {

		model.addAttribute("vo", boardService.getContent(bid));
		model.addAttribute("rpage", rpage);
		boardService.getUpdateHits(bid);
		
		return "/board/board_content";
	}
	
	/**
	 * board_list : 게시판 전체 리스트 
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
