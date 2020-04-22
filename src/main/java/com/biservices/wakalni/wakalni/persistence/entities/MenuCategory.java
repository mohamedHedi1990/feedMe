package com.biservices.wakalni.wakalni.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "wakalni_menu_category")
public class MenuCategory {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Restaurant restaurant;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private List<Menu> menus;
	
	@PrePersist
	private void persistId() {
		this.lastUpdatedDate = new Date();
		if(this.creationDate == null) {
			this.creationDate = new Date();
		}
	}
	
}
