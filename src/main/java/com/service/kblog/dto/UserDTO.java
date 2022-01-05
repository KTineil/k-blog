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
		
	// 아직 쓰이지 않음
	private String token;
	
	private String id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String gender;
	
	private String phone;
	
	public UserDTO(final UserEntity entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.password = entity.getPassword();
		this.gender = entity.getGender();
		this.phone = entity.getPhone();
	}
	
	public UserEntity toEntity() {
		return UserEntity
				.builder()
				.email(email)
				.firstName(firstName)
				.lastName(lastName)
				.password(password)
				.gender(gender)
				.phone(phone)
				.build();
	}
}
