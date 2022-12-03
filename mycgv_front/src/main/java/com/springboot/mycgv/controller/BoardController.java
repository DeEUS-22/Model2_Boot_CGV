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
//	@RequestMapping(value="/board_delete_check.do", method=RequestMethod.POST)
//	public ModelAndView board_delete_check(String bid, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
		
		
		//삭제할 bid 행에 bsfile의 이름을 가져오기 위해 dao.select(bid) 메소드 호출--> upload폴더에 파일 유무 확인
//		CgvBoardVO vo = boardService.getContent(bid); //dao.select(bid) 메소드는 delete 메소드 호출 전에 실행되어야함!! 
//		int result = boardService.getDelete(bid);
		
//		if(result == 1){
			//게시글 삭제시 upload 폴더에 존재하는 파일이 있다면 삭제하기
//			fileService.fileDelete(vo, request);
//			mv.setViewName("redirect:/board_list.do");
//		}else{
//			mv.setViewName("error_page");
//		}
		
//		return mv;
//	}
	
	/**
	 * board_update_check.do : 게시판 수정 처리
	 */
//	@RequestMapping(value="/board_update_check.do", method=RequestMethod.POST)
//	public ModelAndView board_update_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
		
		//기존 파일이 존재하는 경우 이름을 변수로 저장
//		String old_filename = vo.getBsfile();
		
		//수정시 새로운 파일을 선택했는지 확인
//		vo = fileService.update_fileCheck(vo); 
//		int result = boardService.getUpdate(vo);
		
//		if(result == 1){
			//새로운 파일을 upload 폴더에 저장
//			fileService.update_filesave(vo, request, old_filename);
//			mv.setViewName("redirect:/board_list.do");
//		}else{
//			mv.setViewName("error_page");
//		}
		
//		return mv;
//	}
	
	
	
	/**
	 * board_write_check.do : 게시판 글쓰기 처리
	 */
//	@RequestMapping(value="/board_write_check.do", method=RequestMethod.POST)
//	public ModelAndView board_write_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
		
		//1. 파일체크 후 bfile, bsfile에 저장되는 이름 생성
//		vo = fileService.fileCheck(vo);
//		int result = boardService.getWriteResult(vo);
		
//		if(result == 1){
			
			//2. upload 폴더에 bsfile 명으로 실제 파일 업로드 처리
//			fileService.fileSave(vo, request);
			
			//mv.setViewName("/board/board_list"); //에러X, 아무런 게시글 출력되지 X
//			mv.setViewName("redirect:/board_list.do"); //DB연동을 Controller에서 진행하므로, 새로운 연결을 수행!!
//		}else{
//			mv.setViewName("error_page");
//		}
		
//		return mv;
//	}
	
	/**
	 * board_write : 게시판 글쓰기 화면 호출
	 */
//	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	@GetMapping("/board_write")
	public String board_write_get() {
		return "/board/board_write";
	}
	
	/**
	 * board_write : 게시판 글쓰기
	 */
	@PostMapping("/board_write")
	public String board_Write_post(BoardDto boardDto, Model model) throws Exception {
//		System.out.println("btitle-->" + dto.getBtitle());
//		System.out.println("bcontent-->" + dto.getBcontent());
		
		if(fileService.fileCheck(boardDto.getFile1())) {//파일이 있으면 true, 없으면 false
			//boardDto = fileService.init(boardDto);
			boardDto = (BoardDto)fileService.init(boardDto,"board"); // object를 써서 이렇게 적은거다. 아닌 버전은 위에 적은걸 써라
			int result = boardService.getWrite(boardDto);
			if(result == 1) fileService.saveFile(boardDto);
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
	/*
	@GetMapping("/board_delete/{bid}/{bsfile}")
	public String board_delete(@PathVariable String bid, 
			@PathVariable String bsfile,
			Model model) {
		model.addAttribute("bid", bid);
		model.addAttribute("bsfile", bsfile);
		return "/board/board_delete";
	} bsfile의 값이 없을 경우에는(이미지가 없는 경우) delete가 제대로 작동하지 않는 문제가 발생하여 아래와 같은 방식으로 해결했다.
	*/
	@RequestMapping(value={"/board_delete/{bid}", "/board_delete/{bid}/{bsfile}"})
	public String board_delete(@PathVariable(value="bid") String bid, 
								@PathVariable(value="bsfile", required = false) String bsfile,
								Model model) {
		if(bsfile == null) bsfile="none";
//		System.out.println("bsfile-->" + bsfile);
		
		return "/board/board_delete";
	}
	
	
	@PostMapping("/board_delete")
	public String boardDeletePost(BoardDto boardDto) throws Exception{
		int result = boardService.getDelete(boardDto);
		if(result == 1) {
			if(boardDto.getBsfile() != null || !boardDto.getBsfile().equals(""))
			fileService.deleteFile(boardDto.getBsfile());
		}
		return "redirect:/board_list/1";
	}
	
	/**
	 * board_update.do : 게시판 수정 화면
	 */
//	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
//	public ModelAndView board_update(String bid) {
//		ModelAndView mv = new ModelAndView();
		
//		CgvBoardVO vo = boardService.getContent(bid);
		
//		mv.addObject("vo", vo);
//		mv.setViewName("/board/board_update");
		
//		return mv;
//	}
	
	@GetMapping("/board_update/{bid}")
	public String board_update(@PathVariable String bid, Model model) {
		model.addAttribute("vo", boardService.getContent(bid));
		return "/board/board_update";
	}
	
	@PostMapping("/board_update")
	public String boardUpdatePost(BoardDto boardDto) throws Exception{
		
		//여기다가 oldFile을 가져오거다
		//String oldFile = boardDto.getBsfile();
		
		if(fileService.fileCheck(boardDto.getFile1())) { // 파일이 있는지 없는지 먼저 체크해줘야 한다.
			
			//init 메서드 전에 주어야 한다. init메서드가 사용되면 새파일로 올드파일이 대체되기 때문이다.
			String oldFile = boardDto.getBsfile();
			
			//boardDto = fileService.init(boardDto);
			boardDto = (BoardDto)fileService.init(boardDto,"board"); // object를 써서 이렇게 적은거다. 아닌 버전은 위에 적은걸 써라
			int result = boardService.getUpdate(boardDto);
			if(result == 1) {
				fileService.saveFile(boardDto);
				fileService.deleteFile(oldFile);
			}
		}else {
			
		}
		
		return "redirect:/board_list/1";
	}
	
	/**
	 * board_content.do : 게시판 상세 정보
	 */
//	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
//	public ModelAndView board_content(String bid) {
//		ModelAndView mv = new ModelAndView();
		
//		CgvBoardVO vo = boardService.getContent(bid);
//		if(vo != null){
//			boardService.getUpdateHits(bid);
//		}
		
//		mv.addObject("vo", vo);
//		mv.setViewName("/board/board_content");
		
//		return mv;
//	}
	@GetMapping("/board_content/{bid}")
	public String board_content(@PathVariable String bid, Model model) {
		model.addAttribute("vo", boardService.getContent(bid));
		return "/board/board_content";
	}
	/**
	 * board_list.do : 게시판 전체 리스트 
	 */
//	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
//	public ModelAndView board_list(String rpage) {
//		ModelAndView mv = new ModelAndView();

//		Map<String, Integer> param = pageService.getPageResult(rpage, "board", boardService);
//		ArrayList<CgvBoardVO> list = boardService.getList(param.get("startCount"), param.get("endCount"));
		
//		mv.addObject("list", list);
//		mv.addObject("dbCount", param.get("dbCount"));
//		mv.addObject("pageSize", param.get("pageSize"));
//		mv.addObject("rpage", param.get("rpage"));
//		mv.setViewName("/board/board_list");
		
		
//		return mv;
	
//	@GetMapping("/board_list")
//	public String board_list() {
//		return "/board/board_list";
//	}
	
//	@GetMapping("/board_list")
//	public String board_list(Model model) {
//		model.addAttribute("list", boardService.getList());
//		return "/board/board_list";
//	} 페이징 처리하기 이전 버전, 아래는 페이징 처리한 버전
	
//	@GetMapping("/board_list")
//	public String board_list(Model model) {
		
//		PageDto pageDto = pageService.getPageCount("1");
//		model.addAttribute("list", boardService.getList(pageDto));
//		model.addAttribute("page", pageDto);
		
//		return "/board/board_list";
//	}
	
	@GetMapping("/board_list/{rpage}")
	public String board_list(@PathVariable String rpage, Model model) {
		
		PageDto pageDto = pageService.getPageCount(rpage);
		model.addAttribute("list", boardService.getList(pageDto));
		model.addAttribute("page", pageDto);
		
		return "/board/board_list";
	}
	

	
	
	
}