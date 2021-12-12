package com.service.kblog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.kblog.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	boolean existsByEmail(String email);
}
