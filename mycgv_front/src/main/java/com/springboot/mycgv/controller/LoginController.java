package com.springboot.mycgv.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	
	@PostMapping("/login")
	public String login_post(MemberDto memberDto, Model model, HttpSession session) {
		int result = memberService.login(memberDto);
		
		if(result == 1) {
			session.setAttribute("sid", memberDto.getId());
			model.addAttribute("login_result", "ok");
		}
		else model.addAttribute("login_result", "fail");
		
		
		return "/index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		String sid = (String)session.getAttribute("sid");
	
		if(sid != null) session.invalidate();
		model.addAttribute("logout_result", "ok");
		
		return "/index";
	}
	
	
}
