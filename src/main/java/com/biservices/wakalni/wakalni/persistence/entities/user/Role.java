package com.biservices.wakalni.wakalni.persistence.entities.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Table(name = "wakalni_user_role")
@Data
//public class Role implements GrantedAuthority {
public class Role {
	
	@Id
	private String id;

	/*@Override
	public String getAuthority() {
		return id;
	}*/

	public Role(String id) {
		super();
		this.id = id;
	}
	public Role() {
		super();
	}
	

}
