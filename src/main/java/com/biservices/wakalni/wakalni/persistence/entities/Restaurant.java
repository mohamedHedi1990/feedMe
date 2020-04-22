package com.biservices.wakalni.wakalni.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "wakalni_restaurant")
public class Restaurant {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private double latitude;
	private double longititude;
	private String address;
	private String city;
	private String region;
	private String postalCode;
	private double score;
	private double starsAverage;
	private boolean isVip;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Tunis")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginVipDate;
	private String pictureUrl;
	
	private String popularMenus;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//private List<MenuCategory> menuCategories;
	
	
	@PrePersist
	private void persistId() {
		this.lastUpdatedDate = new Date();
		if(this.creationDate == null) {
			this.creationDate = new Date();
		}
	}
	
}
