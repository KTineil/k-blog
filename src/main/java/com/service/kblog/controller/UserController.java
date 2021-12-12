package com.service.kblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.kblog.dto.UserDTO;
import com.service.kblog.model.UserEntity;
import com.service.kblog.service.UserService;

@Controller
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
	public String registPageController() {
		return "register";
	}
	
	@PostMapping("/signup")
	public String registServiceController(UserDTO userDTO) {
		
		UserEntity userEntity = userDTO.toEntity();
		userService.regist(userEntity);
		
		return "login";
	}
	
	@GetMapping("/signin")
	public String loginPageController() {
		return "login";
	}
	
	@PostMapping("/signin")
	public String loginServiceController() {
		return "index";
	}
}
