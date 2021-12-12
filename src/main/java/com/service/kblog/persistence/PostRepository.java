package com.service.kblog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.kblog.model.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String>{

}
