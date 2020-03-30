package com.biservices.wakalni.wakalni.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biservices.wakalni.wakalni.persistence.entities.user.PlatformUser;

import lombok.Data;

@Data
@Entity
@Table(name = "wakalni_comment")
public class Comment {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	private String pictureUrl;
	
	@OneToOne
	private PlatformUser user;
}
