package com.service.kblog.dto;

import java.time.LocalDateTime;

import com.service.kblog.model.PostEntity;

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
	
	public PostDTO(final PostEntity entity) {
		this.id = entity.getId();
		this.writer = new UserDTO(entity.getWriter());
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createdDate = entity.getCreatedDate();
		this.views = entity.getViews();
	}
	
	public PostEntity toEntity() {
		return PostEntity
				.builder()
				.id(id)
				.writer(writer.toEntity())
				.title(title)
				.content(content)
				.createdDate(createdDate)
				.views(views)
				.build();
	}
}
