package com.service.kblog.persistence;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.kblog.model.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String>{

	@Query(nativeQuery = true, value = "select * from post order by created_date desc limit 5")
	LinkedList<PostEntity> findAllWithLimit();
}
