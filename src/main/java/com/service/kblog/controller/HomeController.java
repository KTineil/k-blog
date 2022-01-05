package com.service.kblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.kblog.dto.UserDTO;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping()
	public String indexPage() {
		return "index";
	}
}
