package com.springboot.mycgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	/**
	 * footer.do
	 */
	@GetMapping("/footer")
	public String footer() {
		return "/footer";
	}
	
	/**
	 * header.do
	 */
	@GetMapping("/header")
	public String header() {
		return "/header";
	}
	
	/**
	 * index.do
	 */
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	
	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
}
