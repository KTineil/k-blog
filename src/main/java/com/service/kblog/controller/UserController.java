package com.service.kblog.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.kblog.dto.UserDTO;
import com.service.kblog.model.UserEntity;
import com.service.kblog.security.TokenProvider;
import com.service.kblog.service.UserService;

@Controller
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenProvider tokenProvider;
	
	@GetMapping("/signup")
	public ModelAndView registPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("regist");
		return mav;
	}
	
	@PostMapping("/signup")
	public ModelAndView registService(UserDTO userDTO) {
		
		UserEntity userEntity = userDTO.toEntity();
		userService.regist(userEntity);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		
		return mav;
	}
	
	@GetMapping("/signin")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@PostMapping("/signin")
	public ModelAndView loginService(UserDTO userDTO, HttpServletResponse response) {
		
		UserEntity userEntity = userService.existByCredentials(userDTO.getEmail(), userDTO.getPassword());
		
		ModelAndView mav = new ModelAndView();
		if (userEntity != null) {
			String token = tokenProvider.create(userEntity);
			
			Cookie tokenCookie = new Cookie("jwtToken", token);
			tokenCookie.setMaxAge(60 * 60 * 24);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
			
			mav.setViewName("index");
		}
		
		else {
			mav.setViewName("login");
		}
		
		return mav;
	}
}