package com.service.kblog.service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.kblog.model.PostEntity;
import com.service.kblog.persistence.PostRepository;
import com.service.kblog.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	// post 전체를 읽어오기 위한 메소드
	public LinkedList<PostEntity> retrieveList(int limitStartIndex) {
		LinkedList<PostEntity> posts = postRepository.findAllWithLimit(limitStartIndex);
		if (posts != null) {
			log.info("found" + posts.size() + "posts");
		}
		else {
			log.info("can't found any post");
		}
		return posts;
	}
	
	public int getCntOfPosts() {
		return (int) postRepository.count();
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
