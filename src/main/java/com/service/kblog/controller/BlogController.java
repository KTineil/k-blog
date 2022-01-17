package com.service.kblog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.kblog.dto.PostDTO;
import com.service.kblog.model.PostEntity;
import com.service.kblog.service.PostService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	PostService postService;
	
	@GetMapping()
	public ModelAndView mainBlogPage() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<PostEntity> postEntities = postService.retrieveList();
		List<PostDTO> postDTOs = postEntities.stream().map(PostDTO::new).collect(Collectors.toList());
		
		// model, view 설정
		modelAndView.addObject("posts", postDTOs);
		modelAndView.setViewName("blog");
		
		return modelAndView;
	}
	
	@PostMapping("/post")
	public ModelAndView create() {
		return new ModelAndView("");
	}
	
	@GetMapping("/post")
	public ModelAndView retrieve() {
		return new ModelAndView("");
	}
	
	@PutMapping("/post") 
	public ModelAndView update() {
		return new ModelAndView("");
	}
	
	@DeleteMapping("/post")
	public ModelAndView delete() {
		return new ModelAndView("");
	}
}