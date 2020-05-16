package com.biservices.wakalni.wakalni.persistence.entities.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "wakalni_user")
@Data
public class PlatformUser {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
		
	@Transient
	private String authorization;
	
	private String password;
	private String username;
	private String pictureUrl;
	
	private String firstName;
	private String lastName;
	
	@OneToMany(fetch = FetchType.EAGER)
	List<Role> roles;

	
}
