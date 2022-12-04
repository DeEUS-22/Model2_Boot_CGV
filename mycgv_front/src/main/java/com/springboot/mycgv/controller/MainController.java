package com.springboot.mycgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	/**
	 * 
	 */
	@GetMapping("preparing")
	public String prparing() {
		return "/preparing";
	}
	
	/**
	 * 
	 */
	@GetMapping("error_page")
	public String error() {
		return "/error_page";
	}
	
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
	@GetMapping("/footer") //4버전
	public String footer() {
		return "/footer";
	}
	/**
	 * header.do
	 */
	@GetMapping("/header")
	public String header() {
	//	return "header";
		return "/header";
	}
	
	
	/**
	 * index.do
	 */
	@GetMapping("/index")
	public String index() {
	//	return "index";
		return "/index";
	}
	
}
