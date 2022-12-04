package com.springboot.mycgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
	/**
	 * mycgv
	 */
	@GetMapping("/mycgv")
	public String prparing() {
		return "/mycgv/mycgv";
	}
}
