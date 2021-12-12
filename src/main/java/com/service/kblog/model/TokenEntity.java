package com.service.kblog.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Token")
@Table
public class TokenEntity {
	@Id
	private String token;
	
	private String userId;
	
	private int valid;
}
