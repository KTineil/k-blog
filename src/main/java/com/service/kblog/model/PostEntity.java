package com.service.kblog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Post")
@Table
@Data
public class PostEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	@JoinColumn(name = "userId") // ���� ���� -> �⺻ ����: �ʵ�� + ���� ���̺��� �⺻ Ű �÷���
	private UserEntity userEntity;
	
	private String title;
	
	private String content;
	
	private LocalDateTime createdDate;
	
	private int views;
}