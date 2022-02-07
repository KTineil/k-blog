package com.service.kblog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.kblog.dto.PostDTO;
import com.service.kblog.dto.UserDTO;
import com.service.kblog.model.PostEntity;
import com.service.kblog.persistence.UserRepository;
import com.service.kblog.service.PostService;
import com.service.kblog.util.PageVO;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping()
	public ModelAndView mainBlogPage(@ModelAttribute("pageVO") PageVO pageVO) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		// PageVO에 넘겨줄 게시글의 총 개수를 받아
		int cntOfPosts = postService.getCntOfPosts();
		
		// page 정보를 만듬 
		pageVO.pagination(cntOfPosts);
		
		List<PostEntity> postEntities = postService.retrieveList(pageVO.getLimitStartIndex());
		List<PostDTO> postDTOs = postEntities.stream().map(PostDTO::new).collect(Collectors.toList());
		
		// model, view 설정
		modelAndView.addObject("posts", postDTOs);
		modelAndView.setViewName("blog");
		
		return modelAndView;
	}
	
	@GetMapping("/post/{id}")
	public ModelAndView retrieve(@PathVariable(value="id") final String pid) {
		
		try {
			PostEntity postEntity = postService.retrieve(pid);
			PostDTO postDTO = new PostDTO(postEntity);
			
			ModelAndView modelAndView = new ModelAndView();
			
			modelAndView.addObject("post", postDTO);
			modelAndView.setViewName("blog-single");
			
			return modelAndView;
		}
		catch (Exception e) {
			// 예외처리 
			return new ModelAndView("error");
		}
	}
	
	@GetMapping("/post/write")
	public ModelAndView writePostPage() {
		
		ModelAndView modelAndView = new ModelAndView("blog-write");
		
		return modelAndView;
	}
	
	@PostMapping("/post")
	public ModelAndView create(@AuthenticationPrincipal final String uid, final PostDTO postDTO) {
		
		// postDTO에 writer 정보를 추가해줌.
		UserDTO userDTO = new UserDTO(userRepository.findById(uid).get());
		postDTO.setWriter(userDTO);
		
		// 아이디는 후에 추가되니 null로 주고 DTO를 Entity로 변환해줌.
		PostEntity postEntity = postDTO.toEntity();
		postEntity.setId(null);
		
		// PostEntity를 DB에 추가.
		postService.create(postEntity);
		
		// view로 객체 전달.
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("post", postDTO); // postDTO는 입력받은 변수를 그대로 사용.
		modelAndView.setViewName("blog-single");
		
		return modelAndView;
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