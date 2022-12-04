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

	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	
	@PostMapping("/login")
	public String login_post(MemberDto memberDto, Model model, HttpSession session) {
		int result = memberService.getLogin(memberDto);
		
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
	
	/**
	 * logout.do : �α׾ƿ�
	 */
	/* @RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		//���������� �����ͼ� sid ���� null�� �ƴϸ� session.invalidate �޼ҵ� ȣ��
		//String sid = (String)session.getAttribute("sid");
		SessionVO svo = (SessionVO)session.getAttribute("svo");
		
		if(svo != null) {
			session.invalidate();	//���� ���� ����
			mv.addObject("logout_result","ok"); //index ���������� logout_result ���� �޾Ƽ� ok�� ��� alert â�� ���
		}
		
		mv.setViewName("/index");
		
		return mv;
		
	}
	*/
	
	
	/**
	 * loginCheck.do : �α��� ó��
	 */
/*	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		SessionVO svo = memberService.getLoginResult(vo); 
		
		if(svo != null) {
			if(svo.getLoginresult() == 1){
				//�α��� ���� --> session��ü�� key(sid),value(�α��ΰ���) �߰� �� index �������� �̵�
				//session.setAttribute("sid", vo.getId());
				session.setAttribute("svo", svo);
				mv.addObject("login_result","ok");
				mv.setViewName("index");
			}
		}else{
			mv.addObject("login_result","fail");
			mv.setViewName("/login/login");
		}
				
		return mv;
	}
	*/
	
}
