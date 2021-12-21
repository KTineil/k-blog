package com.service.kblog.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.service.kblog.model.PostEntity;
import com.service.kblog.model.UserEntity;
import com.service.kblog.persistence.PostRepository;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping
	@ResponseBody
	public String testController() {
		
		LocalDateTime now = LocalDateTime.now();
		UserEntity userEntity = new UserEntity("2c9e818a7daedbed017daede6d0d0000", "gurdnrnjs19@gmail.com", "±ÇÇõ¿ì", "dngur19id@@", "M", "01065777824");
		PostEntity entity = new PostEntity("1", userEntity, "title", "content", now, 1);
		postRepository.save(entity);
		
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	@GetMapping("/testJsp")
	public ModelAndView jspTestController() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("date", LocalDateTime.now());
		mav.setViewName("/test");
		
		return mav;
	}
}
