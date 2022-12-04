package com.springboot.mycgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;

@Controller
public class JoinController {
	
	@Autowired
	   private MemberService memberService;
	   
	   //id_check : 아이디 중복 체크  -  Ajax 호출
	   @ResponseBody
	   @GetMapping("/id_check/{id}")
	   public String id_check(@PathVariable String id) {
	      int result = memberService.idCheck(id);
	      
	      return String.valueOf(result);
	   }
	   
	   //joinCheck.do
	 /*  @RequestMapping(value = "/joinCheck", method = RequestMethod.POST)
	   public ModelAndView joinCheck(MemberDto dto) {
	      ModelAndView mv = new ModelAndView();

	      int result = memberService.getJoinResult(dto);
	      
	      if(result == 1) {
	         mv.addObject("join_result", "ok");
	         mv.setViewName("/login/login");
	      }else {
	         mv.setViewName("/error_page");
	      }
	      
	      return mv;
	   }
	   */
		
	   //join.do : 회원가입 폼
	   @GetMapping("/join")
	   public String join() {
	      return "/join/join";
	   }
	   
	   @PostMapping("/join")
	   public String join_post(MemberDto memberdto, Model model) {
		   
		   int result = memberService.getJoin(memberdto);
		   
		   if(result == 1) model.addAttribute("join_result", "ok");
		   else model.addAttribute("join_result", "fail");
		   
		   return "/login/login";
	   }
	   
	   
	}