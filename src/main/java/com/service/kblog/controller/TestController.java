package com.service.kblog.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		UserEntity userEntity = new UserEntity("ff8080817e14346e017e1438b4fa0000", "gurdnrnjs19@naver.com", "권", "혁우", "dngur19id@@", "M", "01065777824");
		PostEntity entity = new PostEntity("1", userEntity, "title", "content", now, 1);
		postRepository.save(entity);
		
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	@GetMapping("/testJsp")
	public ModelAndView jspTestController() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM, d yyyy").withLocale(Locale.forLanguageTag("en"))));
		mav.addObject("origin", LocalDateTime.now());
		mav.setViewName("/test");
		
		return mav;
	}
}
