package com.service.kblog.dto;

import java.time.LocalDateTime;

import com.service.kblog.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostDTO {
	private String id;

	private UserDTO writer;
	
	private String title;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	private int views;
}
