package com.springboot.mycgv.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;

@Controller
public class LoginController {
	
//	@Autowired
//	private MemberServiceImpl memberService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * logout.do : 로그아웃
	 */
	/*
	 * @RequestMapping(value="/logout.do", method=RequestMethod.GET) public
	 * ModelAndView logout(HttpSession session) { ModelAndView mv = new
	 * ModelAndView();
	 * 
	 * //세션정보를 가져와서 sid 값이 null이 아니면 session.invalidate 메소드 호출 //String sid =
	 * (String)session.getAttribute("sid"); SessionVO svo =
	 * (SessionVO)session.getAttribute("svo");
	 * 
	 * if(svo != null) { session.invalidate(); //세션 정보 삭제
	 * mv.addObject("logout_result","ok"); //index 페이지에서 logout_result 값을 받아서 ok인 경우
	 * alert 창을 띄움 }
	 * 
	 * mv.setViewName("/index");
	 * 
	 * return mv;
	 * 
	 * }
	 */
	
	
	
	/**
	 * loginCheck.do : 로그인 처리
	 */
	/*
	 * @RequestMapping(value="/loginCheck.do", method=RequestMethod.POST) public
	 * ModelAndView loginCheck(CgvMemberVO vo, HttpSession session) { ModelAndView
	 * mv = new ModelAndView();
	 * 
	 * SessionVO svo = memberService.getLoginResult(vo);
	 * 
	 * if(svo != null) { if(svo.getLoginresult() == 1){ //로그인 성공 --> session객체에
	 * key(sid),value(로그인계정) 추가 후 index 페이지로 이동 //session.setAttribute("sid",
	 * vo.getId()); session.setAttribute("svo", svo);
	 * mv.addObject("login_result","ok"); mv.setViewName("index"); } }else{
	 * mv.addObject("login_result","fail"); mv.setViewName("/login/login"); }
	 * 
	 * return mv; }
	 */
	
	/**
	 * login.do : 로그인 폼
	 */
//	@RequestMapping(value="/login.do", method=RequestMethod.GET)
//	@GetMapping("/login.do")
//	@GetMapping("/login")
//	public ModelAndView login(String auth) {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("auth", auth);
//		mv.setViewName("/login/login");
//		return mv;
//	}
	
	
	/**
	 * login : 로그인 폼
	 */
	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	
	/**
	 * 
	 */
//	@PostMapping("/login")
//	public String loginPost(MemberDto memberDto, Model model) {
	//	System.out.println("id---->" + memberDto.getId());
	//	System.out.println("pw---->" + memberDto.getPass());

//		int result = memberService.getLogin(memberDto);
		
//		if(result == 1) {
//			model.addAttribute("login_result", "ok");
//		}
//		else model.addAttribute("login_result", "fail");
//		return "/index";
//	} 세션처리 이전 버전 아래는 세션처리 이후 버전
	
	@PostMapping("/login")
	public String loginPost(MemberDto memberDto, Model model, HttpSession session) {
	//	System.out.println("id---->" + memberDto.getId());
	//	System.out.println("pw---->" + memberDto.getPass());

		int result = memberService.getLogin(memberDto);
		
		if(result == 1) {
			//세션객체에 sid 추가하기
			session.setAttribute("sid", memberDto.getId());
			model.addAttribute("login_result", "ok");
		}
		else model.addAttribute("login_result", "fail");
		return "/index";
	}
	
	/**
	 * logout
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) { 
	  
		String sid = (String)session.getAttribute("sid");
		
		if(sid != null)  session.invalidate(); //세션 정보 삭제
		
		model.addAttribute("logout_result","ok"); //index 페이지에서 logout_result 값을 받아서 ok인 경우 alert 창을 띄움 }
		
		return "/index";
	  
	}
	
}
