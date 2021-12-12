package com.service.kblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.kblog.model.UserEntity;
import com.service.kblog.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public void regist(final UserEntity userEntity) {
		if (userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = userEntity.getEmail();
		if (userRepository.existsByEmail(email)) {
			log.warn("Email already exist");
			throw new RuntimeException("Email already exist");
		}
		
		log.info("User is created successfully");
		userRepository.save(userEntity);
	}
	
}
