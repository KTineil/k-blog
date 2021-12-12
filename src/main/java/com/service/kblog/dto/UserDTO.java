package com.service.kblog.dto;

import com.service.kblog.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {
		
	private String token;
	
	private String id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String gender;
	
	private String phone;
	
	public UserEntity toEntity() {
		return UserEntity
				.builder()
				.email(email)
				.name(firstName+lastName)
				.password(password)
				.gender(gender)
				.phone(phone)
				.build();
	}
}
