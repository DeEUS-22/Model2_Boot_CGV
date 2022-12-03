package com.springboot.mycgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	/**
	 * "/"
	 */
	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
	/**
	 * footer.do
	 */
	/*
	@RequestMapping(value="/footer.do", method=RequestMethod.GET) - 3버전
	public String footer() {
		return "footer";
	}
	*/
	@GetMapping("/footer") //4버전
	public String footer() {
		return "/footer";
	}
	/**
	 * header.do
	 */
	//@RequestMapping(value="/header.do", method=RequestMethod.GET)
	@GetMapping("/header")
	public String header() {
	//	return "header";
		return "/header";
	}
	
	
	/**
	 * index.do
	 */
	//@RequestMapping(value="/index.do", method=RequestMethod.GET)
	@GetMapping("/index")
	public String index() {
	//	return "index";
		return "/index";
	}
	
}
