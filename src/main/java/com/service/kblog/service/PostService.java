package com.service.kblog.service;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.kblog.model.PostEntity;
import com.service.kblog.persistence.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	// post 전체를 읽어오기 위한 메소드
	public LinkedList<PostEntity> retrieveList() {
		
		LinkedList<PostEntity> posts = postRepository.findAllWithLimit();
		log.info(posts.get(0).getTitle());
		
		return posts;
	}
	
	public void create() {
		
	}
	
	public void retrieve() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
}
