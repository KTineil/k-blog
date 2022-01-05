package com.service.kblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "User")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
	
	// �⺻ ����
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	private String email;
	
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@Column(length = 20)
	private String password;
	
	// �߰� ����
	@Column(columnDefinition = "char(1)")
	private String gender;
	
	@Column(columnDefinition = "char(11)")
	private String phone;
}