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
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * login : 로그인 폼
	 */
	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	
	@PostMapping("/login")
	public String loginPost(MemberDto memberDto, Model model, HttpSession session) {

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
