package com.service.kblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.kblog.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/testRequestBody")
	public String testController(@RequestBody TestRequestBodyDTO testRequestBodyDTO)  {
		return "Hello Get " + testRequestBodyDTO.getId() + " " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testRequestParam")
	public String testController(@RequestParam(required = false) int id) {
		return "Hello Get Id " + id;
	}
}