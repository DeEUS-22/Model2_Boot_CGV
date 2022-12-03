package com.springboot.mycgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;

@Controller
public class JoinController {
	
	/*
	@Autowired
	private MemberServiceImpl memberService;
	*/
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * id_check.do : 아이디 중복체크 처리 - Ajax 호출
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/id_check.do", method=RequestMethod.GET) public String
	 * idCheck(String id) { int result = memberService.getIdCheck(id); return
	 * String.valueOf(result); }
	 */
	
	
	/**
	 * joinCheck.do : 회원가입 처리
	 */
	/*
	 * @RequestMapping(value="/joinCheck.do", method=RequestMethod.POST) public
	 * ModelAndView joinCheck(CgvMemberVO vo) { ModelAndView mv = new
	 * ModelAndView();
	 * 
	 * int result = memberService.getJoinResult(vo);
	 * 
	 * if(result == 1){ mv.addObject("join_result","ok");
	 * mv.setViewName("/login/login"); }else{ mv.setViewName("error_page"); }
	 * 
	 * return mv; }
	 */
	
	/**
	 * join.do : 회원가입 폼
	 */
	//@RequestMapping(value="/join.do", method=RequestMethod.GET)
//	@GetMapping("/join.do")
	@GetMapping("/join")
	public String joinGet() {
		return "/join/join";
	}
	
	@PostMapping("/join")
	public String joinPost(MemberDto memberDto, Model model) {
		/*
		 * System.out.println(memberDto.getId());
		 * System.out.println(memberDto.getPass());
		 * System.out.println(memberDto.getEmail());
		 */
		
		int result = memberService.getJoin(memberDto);
//		return "redirect:/";
		
		if(result == 1) model.addAttribute("join_result", "ok");
		return "/login/login";
	}
}
